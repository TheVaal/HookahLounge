package com.example.hookahlounge.presentation.tobacco.viewmodel.single

import androidx.lifecycle.viewModelScope
import com.example.hookahlounge.domain.model.Hardness
import com.example.hookahlounge.domain.model.LoungeTobacco
import com.example.hookahlounge.domain.model.Manufacturer
import com.example.hookahlounge.domain.model.Tobacco
import com.example.hookahlounge.domain.usecase.HardnessUseCase
import com.example.hookahlounge.domain.usecase.LoungeTobaccoUseCase
import com.example.hookahlounge.domain.usecase.ManufacturerUseCase
import com.example.hookahlounge.domain.usecase.TobaccoUseCase
import com.example.hookahlounge.domain.util.AppCoroutineDispatchers
import com.example.hookahlounge.domain.util.BaseViewModel
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import com.example.hookahlounge.presentation.tobacco.viewmodel.ManufacturersHardness
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TobaccoViewModel @Inject constructor(
    private val dispatchers: AppCoroutineDispatchers,
    private val tobaccoUseCase: TobaccoUseCase,
    private val manufacturerUseCase: ManufacturerUseCase,
    private val hardnessUseCase: HardnessUseCase,
    private val loungeTobaccoUseCase: LoungeTobaccoUseCase,
) : BaseViewModel<LoungeTobaccoUiState>() {
    override val initialState: LoungeTobaccoUiState
        get() = LoungeTobaccoUiState()

    fun loadData(
        loungeId: Long,
        tobaccoId: Long,
        loungeTobaccoId: Long,
    ) {
        if (loungeId == 0L && loungeTobaccoId == 0L && tobaccoId == 0L) return
        state = state.copy(isLoading = true)
        viewModelScope.launch(dispatchers.io + job) {
            loadSecondaryData().collect {
                it.onSuccess {
                    if (tobaccoId != 0L) {
                        loadByTobacco(tobaccoId)

                    } else if (loungeTobaccoId != 0L) {
                        loadByLoungeTobacco(loungeTobaccoId)
                    }
                }
                    .onError { state = state.copy(isLoading = false) }
            }


        }
        editLoungeTobacco(state.loungeTobacco.copy(loungeId = loungeId))
        state = state.copy(isLoading = false)
    }

    private suspend fun loadSecondaryData(): Flow<HookahResponse<ManufacturersHardness>> {
        return hardnessUseCase.loadHardness()
            .combine(manufacturerUseCase.loadManufacturers()) { hardnessResponse, manufacturerResponse ->
                if (hardnessResponse is HookahResponse.Success && manufacturerResponse is HookahResponse.Success) {
                    state = state.copy(
                        hardnessList = hardnessResponse.data,
                        manufacturers = manufacturerResponse.data
                    )
                    HookahResponse.Success(
                        ManufacturersHardness(
                            manufacturersList = manufacturerResponse.data,
                            hardnessList = hardnessResponse.data
                        )
                    )

                } else {
                    HookahResponse.Error(errorMessage = "Unable to retrieve data")
                }
            }
    }

    private suspend fun loadByLoungeTobacco(loungeTobaccoId: Long) {
        loungeTobaccoUseCase.loadTobaccoById(loungeTobaccoId).collect {
            it.onSuccess { loungeTobacco ->
                state = state.copy(loungeTobacco = loungeTobacco)
            }
        }
    }

    private suspend fun loadByTobacco(tobaccoId: Long) {
        tobaccoUseCase.loadTobaccoById(tobaccoId).collect {
            it.onSuccess { tobacco ->
                editLoungeTobacco(
                    state.loungeTobacco.copy(
                        tobacco = tobacco,
                        tobaccoId = tobaccoId
                    )
                )
            }
        }
    }

    private fun editLoungeTobacco(loungeTobacco: LoungeTobacco) {
        state = state.copy(loungeTobacco = loungeTobacco)
    }

    fun onEvent(event: LoungeTobaccoEvent) {
        when (event) {
            is LoungeTobaccoEvent.EnteredManufacturerName -> {
                val search = state.manufacturers.findLast { it.name == event.value }
                val manufacturerId = search?.id ?: 0L
                val manufacturer = state.loungeTobacco.tobacco.manufacturer.copy(name = event.value, id = manufacturerId)


                val tobacco = state.loungeTobacco.tobacco.copy(
                    manufacturer = manufacturer,
                    manufacturerId = manufacturerId
                )
                editLoungeTobacco(loungeTobacco = state.loungeTobacco.copy(tobacco = tobacco))
            }

            is LoungeTobaccoEvent.EnteredPrice -> {
                editLoungeTobacco(loungeTobacco = state.loungeTobacco.copy(price = event.value))
            }

            is LoungeTobaccoEvent.EnteredTaste -> {
                val tobacco = state.loungeTobacco.tobacco.copy(taste = event.value)
                editLoungeTobacco(loungeTobacco = state.loungeTobacco.copy(tobacco = tobacco))
            }

            is LoungeTobaccoEvent.SelectedHardness -> {
                val tobacco = state.loungeTobacco.tobacco.copy(
                    hardness = event.value,
                    hardnessId = event.value.id
                )
                editLoungeTobacco(loungeTobacco = state.loungeTobacco.copy(tobacco = tobacco))
            }

            is LoungeTobaccoEvent.SelectedManufacturerName -> {
                val tobacco = state.loungeTobacco.tobacco.copy(
                    manufacturer = event.value,
                    manufacturerId = event.value.id
                )
                editLoungeTobacco(loungeTobacco = state.loungeTobacco.copy(tobacco = tobacco))
            }
        }
    }

    fun postTobacco() {
        state = state.copy(isLoading = true)
        viewModelScope.launch(dispatchers.io + job) {
            postManufacturerData().onSuccess {
                postHardnessData().onSuccess {
                    postTobaccoData().onSuccess {
                        postLoungeTobaccoData()
                    }
                }
            }
            state = state.copy(isLoading = false)
        }

    }

    private suspend fun postManufacturerData(): HookahResponse<Manufacturer> {
        return if (state.loungeTobacco.tobacco.manufacturer.id == 0L) {
            manufacturerUseCase.postManufacturer(state.loungeTobacco.tobacco.manufacturer)
                .onSuccess { manufacturer ->
                    val tobacco = state.loungeTobacco.tobacco.copy(
                        manufacturer = manufacturer,
                        manufacturerId = manufacturer.id
                    )
                    editLoungeTobacco(loungeTobacco = state.loungeTobacco.copy(tobacco = tobacco))
                }

        } else {
            manufacturerUseCase.putManufacturer(state.loungeTobacco.tobacco.manufacturer)
        }.onError {
            state = state.copy(isLoading = false)
        }
    }

    private suspend fun postHardnessData(): HookahResponse<Hardness> {
        return if (state.loungeTobacco.tobacco.hardness.id == 0L) {
            hardnessUseCase.postHardness(state.loungeTobacco.tobacco.hardness)
                .onSuccess { hardness ->
                    val tobacco = state.loungeTobacco.tobacco.copy(
                        hardness = hardness,
                        hardnessId = hardness.id
                    )
                    editLoungeTobacco(loungeTobacco = state.loungeTobacco.copy(tobacco = tobacco))
                }
        } else {
            hardnessUseCase.putHardness(state.loungeTobacco.tobacco.hardness)
        }.onError {
            state = state.copy(isLoading = false)
        }
    }

    private suspend fun postTobaccoData(): HookahResponse<Tobacco> {
        return if (state.loungeTobacco.tobacco.id == 0L) {
            tobaccoUseCase.postTobacco(state.loungeTobacco.tobacco)
                .onSuccess { tobacco ->
                    editLoungeTobacco(loungeTobacco = state.loungeTobacco.copy(tobacco = tobacco))
                }
        } else {
            tobaccoUseCase.putTobacco(state.loungeTobacco.tobacco)
        }.onError {
            state = state.copy(isLoading = false)
        }
    }

    private suspend fun postLoungeTobaccoData(): HookahResponse<LoungeTobacco> {
        return if (state.loungeTobacco.id == 0L) {
            loungeTobaccoUseCase.postTobacco(state.loungeTobacco)
                .onSuccess { loungeTobacco ->
                    state = state.copy(loungeTobacco = loungeTobacco)
                }
        } else {
            loungeTobaccoUseCase.putLoungeTobacco(state.loungeTobacco)
        }.onSuccess {
            state = state.copy(isLoading = false)
        }.onError {
            state = state.copy(isLoading = false)
        }
    }
}

