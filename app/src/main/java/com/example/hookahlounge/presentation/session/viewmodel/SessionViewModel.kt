package com.example.hookahlounge.presentation.session.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.hookahSession.domain.usecase.SessionUseCase
import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.domain.usecase.LoungeUseCase
import com.example.hookahlounge.domain.util.AppCoroutineDispatchers
import com.example.hookahlounge.domain.util.BaseViewModel
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class SessionStatus(val value: String) {
    BOOKED("BD"),
    CANCELED("CN"),
    PREORDERED("PO"),
    PAID("PD")
}

const val ACCESS_CODE_SIZE: Int = 8

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionUseCase: SessionUseCase,
    private val loungeUseCase: LoungeUseCase,
    private val dispatchers: AppCoroutineDispatchers,
) : BaseViewModel<SessionUiState>() {

    override val initialState: SessionUiState
        get() = SessionUiState()

    fun loadData(id: Long) {
        if (id != 0L) {
            viewModelScope.launch(dispatchers.io + job) {
                state = state.copy(isLoading = true)
                sessionUseCase.loadSessionById(id).collect { result ->
                    result.onSuccess { session ->
                        state = state.copy(
                            isLoading = false,
                            session = session
                        )

                        loungeUseCase.loadLounges().collect { result ->
                            result.onSuccess { lounges ->
                                state = state.copy(lounges = lounges)
                                lounges.find { session.loungeId == it.id }
                                    ?.let { session.copy(lounge = it) }?.let { editSession(it) }

                            }.onError {
                                state = state.copy(lounges = listOf(state.session.lounge))
                            }
                        }

                    }
                }
            }
        } else {
            viewModelScope.launch(dispatchers.io + job) {
                state = state.copy(isLoading = true)
                loungeUseCase.loadLounges().collect { result ->
                    result.onSuccess { lounges ->
                        state = state.copy(
                            lounges = lounges,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun postSession() {
        state = state.copy(
            isLoading = true
        )

        viewModelScope.launch(dispatchers.io + job) {
            val result = if (state.session.id == 0L) {
                sessionUseCase.postSession(state.session)
            } else {
                sessionUseCase.putSession(state.session)
            }
            result.onSuccess {
                editSession(it)
            }

            state = state.copy(
                isLoading = false
            )
        }
    }

    fun onEvent(event: SessionEvent) {
        when (event) {
            is SessionEvent.EnteredName -> {
                editSession(state.session.copy(ownerName = event.value))
                generateAccessCode()
            }

            is SessionEvent.EnteredDate -> {
                editDate(event.value)
                generateAccessCode()
            }

            is SessionEvent.EnteredCountry -> {
                editSession(state.session.copy(ownerCountryCode = event.value))
                generateAccessCode()
            }

            is SessionEvent.EnteredPhone -> {
                editSession(state.session.copy(ownerId = event.value))
                generateAccessCode()
            }

            is SessionEvent.EnteredStatus -> {
                editSession(state.session.copy(status = event.value))
                generateAccessCode()
            }

            is SessionEvent.EnteredTime -> {
                editTime(event.value)
                generateAccessCode()
            }

            is SessionEvent.EnteredLounge -> {
                editSession(
                    state.session.copy(
                        loungeId = event.value.id,
                        lounge = event.value
                    )
                )
            }
        }
    }

    private fun generateAccessCode() {
        val base = state.session.id.toString()
            .plus(state.session.ownerCountryCode)
            .plus(state.session.bookingDate)
            .replace("-", "")
            .plus(state.session.ownerName)
            .replace(" ", "")
            .replace(":", "")
            .uppercase()
        var accessCode = ""
        for (i in 1..ACCESS_CODE_SIZE) {
            accessCode += base.random()
        }
        editSession(state.session.copy(accessCode = accessCode))
    }

    private fun editDate(value: String) {
        val session: Session = state.session
        val bookingDate = session.bookingDate
        if (bookingDate == "") {
            editSession(session.copy(bookingDate = value))
        } else {
            val currentTime = session.bookingDate
                .substring(10, session.bookingDate.length)
                .replace(" ", "")
            editSession(
                session.copy(
                    bookingDate = value.plus(" $currentTime")
                )
            )
        }
    }

    private fun editTime(value: String) {
        val session = state.session
        val currentDate = session.bookingDate
            .substring(0, 10)
            .replace(" ", "")
        editSession(
            session.copy(
                bookingDate = currentDate.plus(" $value")
            )
        )
    }

    private fun editSession(session: Session) {
        state = state.copy(session = session)
    }

}


