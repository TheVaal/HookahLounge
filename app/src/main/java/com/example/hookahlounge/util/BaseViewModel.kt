package com.example.hookahlounge.util

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<T> : ViewModel() {

    protected abstract val initialState: T
    private val _uiState: MutableStateFlow<T> by lazy { MutableStateFlow(initialState) }
    open val uiState = _uiState.asStateFlow()
    protected open var state: T
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private val _snackbarMessage = MutableSharedFlow<SnackbarMessage>(extraBufferCapacity = 1)
    val snackbarMessage = _snackbarMessage.asSharedFlow()

    private var _job = Job()
    protected val job: CompletableJob
        get() {
            _job.cancelChildren()
            _job.cancel()
            _job = Job()
            return _job
        }

    protected fun cancelJob() {
        _job.cancelChildren()
        _job.cancel()
    }

    private fun errorSnackbar(message: String): SnackbarMessage {
        val duration = if (message.length > 150) SnackbarDuration.Long else SnackbarDuration.Short
        return SnackbarMessage.StringMessage(message, duration = duration)
    }

    fun emitSnackbarMessage(
        resourceId: Int,
        duration: SnackbarDuration = SnackbarDuration.Short,
        onDismiss: (() -> Unit)? = null
    ) {
        _snackbarMessage.tryEmit(SnackbarMessage.ResourceMessage(resourceId, duration, onDismiss))
    }

    fun emitSnackbarMessage(
        message: String,
        duration: SnackbarDuration = SnackbarDuration.Short,
        onDismiss: (() -> Unit)? = null
    ) {
        _snackbarMessage.tryEmit(SnackbarMessage.StringMessage(message, duration, onDismiss))
    }

//    fun emitSnackbarMessage(t: Throwable?) {
//        if (t is RuntimeException) {
//            _snackbarMessage.tryEmit(SnackbarMessage.ResourceMessage(t.message))
//
//        } else if (!t?.message.isNullOrEmpty())
//            _snackbarMessage.tryEmit(errorSnackbar(t!!.message!!))
//        else
//            _snackbarMessage.tryEmit(SnackbarMessage.ResourceMessage("R.string.unknown_error"))
//    }

    fun emitSnackbarMessage(snackbarMessage: SnackbarMessage) {
        _snackbarMessage.tryEmit(snackbarMessage)
    }

}