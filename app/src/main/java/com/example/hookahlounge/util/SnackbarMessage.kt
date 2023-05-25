package com.example.hookahlounge.util

import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration


sealed class SnackbarMessage(
    open val duration: SnackbarDuration,
    open val onDismiss: (() -> Unit)?,
    open val actionLabel: String?,
    open val onAction: (() -> Unit)?,
) {
    data class StringMessage(
        val message: String,
        override val duration: SnackbarDuration = SnackbarDuration.Short,
        override val onDismiss: (() -> Unit)? = null,
        override val actionLabel: String? = null,
        override val onAction: (() -> Unit)? = null,
    ) : SnackbarMessage(
        duration = duration,
        onDismiss = onDismiss,
        actionLabel = actionLabel,
        onAction = onAction,
    )

    data class ResourceMessage(
        @StringRes val resourceId: Int,
        override val duration: SnackbarDuration = SnackbarDuration.Short,
        override val onDismiss: (() -> Unit)? = null,
        override val actionLabel: String? = null,
        override val onAction: (() -> Unit)? = null,
        val args: List<String> = emptyList(),
    ) : SnackbarMessage(
        duration = duration,
        onDismiss = onDismiss,
        actionLabel = actionLabel,
        onAction = onAction,
    )
}