package com.example.hookahlounge.presentation.hookah_ui_elements

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun HookahTextField(
    value: String,
    label: String,
    multiline: Boolean = false,
    readOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    width: Float = 1f,
    onValueChange: (String) -> (Unit),
) {
    var state by remember { mutableStateOf(value) }
    OutlinedTextField(
        value = value,
        onValueChange = {
            state = it
            onValueChange(state)
        },
        visualTransformation = visualTransformation,
        modifier = Modifier.fillMaxWidth(width),
        singleLine = !multiline,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        label = { Text(label) }
    )
}

@Composable
fun <T> HookahSelectBox(
    options: List<T>,
    currentValue: T,
    width: Float = 1f,
    menuWidth: Float = 0.5f,
    getter: (T) -> (String),
    onEvent: (T) -> (Unit),
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxWidth(width).padding(4.dp)) {
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Expand")
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(menuWidth),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { value ->
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    onClick = {
                        expanded = false
                        // Do something with selected status
                        onEvent(value)

                    },
                    enabled = value != currentValue,
                    text = { Text(getter(value)) }
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HookahDateInput(onDateConfirm: (Long) -> (Unit), onTimeConfirm: (String) -> (Unit)) {
    // Decoupled snackbar host state from scaffold state for demo purposes.
    val snackState = remember { SnackbarHostState() }
    SnackbarHost(hostState = snackState, Modifier)
    val openDialog = remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        IconButton(
            onClick = { openDialog.value = true }
        ) {
            Icon(
                Icons.Filled.DateRange,
                "Show Date time picker dialog",
            )
        }
    }
    SnackbarHost(hostState = snackState)

    if (openDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
        DatePickerDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        datePickerState.selectedDateMillis?.let { onDateConfirm(it) }
                        showTimePicker = true
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
    HookahTimePicker(showTimePicker = showTimePicker, onTimeConfirm = onTimeConfirm) { value ->
        showTimePicker = value
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HookahTimePicker(
    showTimePicker: Boolean,
    onTimeConfirm: (String) -> (Unit),
    onShowTimePickerChange: (Boolean) -> (Unit),
) {
    val state = rememberTimePickerState()

    if (showTimePicker) {

        DatePickerDialog(
            onDismissRequest = { onShowTimePickerChange(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.HOUR_OF_DAY, state.hour)
                        cal.set(Calendar.MINUTE, state.minute)
                        cal.isLenient = false
                        val hour: String = if (state.hour < 10) {
                            "0${state.hour}"
                        } else {
                            state.hour.toString()
                        }
                        val minute: String = if (state.minute < 10) {
                            "0${state.minute}"
                        } else {
                            state.minute.toString()
                        }
                        onTimeConfirm("${hour}:${minute}:00")
                        onShowTimePickerChange(false)
                    }
                ) {
                    Text("Ok")
                }

            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onShowTimePickerChange(false)
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            TimePicker(modifier = Modifier.fillMaxWidth(), state = state)

        }
    }
}
