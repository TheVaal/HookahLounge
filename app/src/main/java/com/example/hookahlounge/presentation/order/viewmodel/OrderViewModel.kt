package com.example.hookahlounge.presentation.order.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.hookahlounge.domain.model.Hookah
import com.example.hookahlounge.domain.model.InOrder
import com.example.hookahlounge.domain.model.Mix
import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.domain.repository.datastore.UserPrefsDataStoreRepository
import com.example.hookahlounge.domain.usecase.HookahMixUseCase
import com.example.hookahlounge.domain.usecase.InOrderUseCase
import com.example.hookahlounge.domain.usecase.LoungeUseCase
import com.example.hookahlounge.domain.usecase.OrderUseCase
import com.example.hookahlounge.domain.usecase.SessionUseCase
import com.example.hookahlounge.domain.util.AppCoroutineDispatchers
import com.example.hookahlounge.domain.util.BaseViewModel
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import com.example.hookahlounge.presentation.session.viewmodel.SessionStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val dispatchers: AppCoroutineDispatchers,
    private val orderUseCase: OrderUseCase,
    private val inOrderUseCase: InOrderUseCase,
    private val loungeUseCase: LoungeUseCase,
    private val hookahMixUseCase: HookahMixUseCase,
    private val userPrefsDataStoreRepository: UserPrefsDataStoreRepository,
    private val sessionUseCase: SessionUseCase,
) : BaseViewModel<OrderUiState>() {
    override val initialState: OrderUiState
        get() = OrderUiState()

    fun loadData(orderId: Long) {
        if (orderId == 0L && state.order.id == 0L) {
            viewModelScope.launch(dispatchers.io + job) {
                loadLounge()
            }
            return
        }
        val currId = if (orderId == 0L) state.order.id else orderId

        viewModelScope.launch(dispatchers.io + job) {
            state = state.copy(isLoading = true)
            orderUseCase.loadOrderById(orderId = currId).collect {
                it.onSuccess { order ->
                    state = state.copy(order = order)
                    loadLounge()
                    state = state.copy(isLoading = false)
                }.onError {
                    state = state.copy(isLoading = false)
                }
            }
        }
        state = state.copy(isLoading = false)
    }

    @Suppress("DeferredResultUnused")
    private suspend fun loadLounge() {
        coroutineScope {
            val loungeId = if (state.order.session.loungeId == 0L) {
                userPrefsDataStoreRepository.getData().first().currentWorkplace
            } else {
                state.order.session.loungeId
            }
            loungeUseCase.loadLoungeById(loungeId).collect { response ->
                response.onSuccess { lounge ->
                    state = state.copy(lounge = lounge)
                    async { loadMixes() }
                    async { loadInOrder() }
                    async { loadSessions() }

                }
            }
        }
    }

    private suspend fun loadSessions() {
        sessionUseCase.loadSessions().collect { response ->
            response.onSuccess { sessions ->
                state = state.copy(
                    sessions = sessions,
                )
            }
        }
    }

    private suspend fun loadInOrder() {
        if (state.order.id == 0L) return

        inOrderUseCase.loadInOrder(state.order.id).collect { response ->
            response.onSuccess { inOrder ->
                state = state.copy(inOrder = inOrder.toMutableList())
                updateSum()
                guests()
            }
        }
    }

    private suspend fun loadMixes() {
        if (state.order.id == 0L) return
        hookahMixUseCase.loadHookahMix(state.order.id).collect { response ->
            response.onSuccess { mixes ->
                state = state.copy(
                    mixes = mixes,
                )
                updateSum()
            }
        }
    }

    private fun guests(): String {
        val maxValue = state.inOrder.maxWithOrNull(Comparator.comparingInt { it.guest })?.guest
        state = state.copy(
            guests = if (maxValue == null) {
                state.guests
            } else if (state.guests.toInt() >= maxValue) {
                state.guests
            } else {
                maxValue.toString()
            }
        )
        return state.guests
    }

    private fun postInOrder(inOrder: InOrder) {
        if (state.order.id == 0L) return
        viewModelScope.launch(dispatchers.io + job) {
            inOrderUseCase.postInOrder(inOrder).onSuccess {
                val list = state.inOrder.toMutableList()
                list.add(inOrder.copy(id = it.id))
                state = state.copy(inOrder = list)
                updateSum()
            }
        }
    }

    private fun putInOrder(inOrder: InOrder) {
        if (state.order.id == 0L) return
        if (inOrder.quantity == "") return
        if (inOrder.quantity.toDouble() == 0.0) return
        viewModelScope.launch(dispatchers.io + job) {
            inOrderUseCase.putInOrder(inOrder)
        }
    }

    fun onEvent(event: OrderUIEvent) {
        when (event) {
            is OrderUIEvent.AddInOrder -> {
                postInOrder(
                    InOrder(
                        orderId = state.order.id,
                        guest = event.guest,
                        quantity = event.quantity.toString(),
                        menu = event.menu,
                        menuId = event.menu.id
                    )
                )
            }

            is OrderUIEvent.ChangeStatus -> {
                state = state.copy(order = state.order.copy(status = event.value))
            }

            is OrderUIEvent.SelectedSession -> {
                selectSession(event)
            }

            is OrderUIEvent.SelectedTable -> {
                state = state.copy(
                    order = state.order.copy(
                        table = event.value,
                        tableId = event.value.id
                    )
                )
            }

            is OrderUIEvent.ChangeQuantity -> {
                changeQuantity(event)
            }

            is OrderUIEvent.ChangeGuests -> {
                state = state.copy(guests = event.value)
            }

            is OrderUIEvent.PostOrder -> {
                if (state.order.id == 0L) {
                    postOrder()
                } else {
                    putOrder()
                }
            }

            is OrderUIEvent.AddHookah -> {
                postHookah(
                    Hookah(
                        mixId = event.mixId,
                        loungeTobaccoId = event.tobacco.id,
                        loungeTobacco = event.tobacco,
                        weight = event.weight
                    )
                )
            }

            is OrderUIEvent.AddMix -> {
                postMix(
                    Mix(
                        orderId = state.order.id,
                        weight = "0"
                    )
                )
            }
        }
    }

    private fun postHookah(hookah: Hookah) {
        if (state.order.id == 0L) return
        viewModelScope.launch(dispatchers.io + job) {
            hookahMixUseCase.postHookah(hookah = hookah).onSuccess {
                loadMixes()
            }
        }
    }

    private fun postMix(newMix: Mix) {
        if (state.order.id == 0L) return
        viewModelScope.launch(dispatchers.io + job) {
            hookahMixUseCase.postMix(newMix).onSuccess { mix ->

                val list = state.mixes.toMutableList()
                list.add(mix)
                state = state.copy(mixes = list)

                updateSum()
            }
        }
    }

    private fun selectSession(event: OrderUIEvent.SelectedSession) {
        state = state.copy(
            order = state.order.copy(
                session = event.value,
                sessionId = event.value.id
            )
        )
        viewModelScope.launch(dispatchers.io + job) {
            loadLounge()
        }
    }

    private fun changeQuantity(event: OrderUIEvent.ChangeQuantity) {
        val list = state.inOrder.toMutableList()
        val inOrder = state.inOrder.findLast {
            it == event.inOrder
        } ?: return
        list.forEachIndexed { index, obj ->
            if (obj.id == inOrder.id) {
                list[index] = inOrder.copy(quantity = event.newValue)
                putInOrder(list[index])
            }
        }
        state = state.copy(inOrder = list.toList())
        updateSum()
    }

    private fun updateSum() {
        val list = state.inOrder
            .filter { it.quantity != "" }
            .map {
                it.quantity.toDouble() * it.menu.price.toDouble()
            }
        state = state.copy(order = state.order.copy(sum = list.sum()))
    }

    private fun postOrder() {
        viewModelScope.launch(dispatchers.io + job) {

            if (state.order.session.id == 0L) {
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val bookingDate = LocalDateTime.now().format(formatter)
                sessionUseCase.postSession(
                    Session(
                        ownerName = "Guest",
                        status = SessionStatus.BOOKED.value,
                        accessCode = "#######",
                        loungeId = userPrefsDataStoreRepository.getData().first().currentWorkplace,
                        bookingDate = bookingDate
                    )
                ).onSuccess { session ->
                    state = state.copy(order = state.order.copy(sessionId = session.id))
                    orderUseCase.postOrder(state.order).onSuccess { order ->
                        state = state.copy(order = state.order.copy(id = order.id))
                    }
                }
            } else {
                orderUseCase.postOrder(state.order).onSuccess {
                    state = state.copy(order = state.order.copy(id = it.id))
                }
            }


        }
    }

    private fun putOrder() {
        viewModelScope.launch(dispatchers.io + job) {
            orderUseCase.putOrder(state.order).onSuccess {
                state = state.copy(order = it)
            }
        }
    }
}

