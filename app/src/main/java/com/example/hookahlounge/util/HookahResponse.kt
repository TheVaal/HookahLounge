package com.example.hookahlounge.util

sealed interface HookahResponse<out T> {
    class Success<T>(val data: T) : HookahResponse<T>
    class Error(val errorMessage: String) : HookahResponse<Nothing>
    object Loading : HookahResponse<Nothing>


}
/**
 * The block() will be called when it is a success
 */
inline fun <R> HookahResponse<R>.onSuccess(block: (R) -> Unit): HookahResponse<R> {
    if (this is HookahResponse.Success) {
        block(data)
    }
    return this
}

/**
 * The block() will be called when it is an error
 */
inline fun <R> HookahResponse<R>.onError(block: (exception: String?) -> Unit): HookahResponse<R> {
    if (this is HookahResponse.Error) {
        block(errorMessage)
    }
    return this
}