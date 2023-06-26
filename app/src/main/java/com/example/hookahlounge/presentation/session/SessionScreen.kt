package com.example.hookahlounge.presentation.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahDateInput
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahSelectBox
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahTextField
import com.example.hookahlounge.presentation.hookah_ui_elements.PhoneNumberVisualTransformation
import com.example.hookahlounge.presentation.hookah_ui_elements.TitleSmall
import com.example.hookahlounge.presentation.session.viewmodel.SessionEvent
import com.example.hookahlounge.presentation.session.viewmodel.SessionUiState
import com.example.hookahlounge.presentation.session.viewmodel.SessionViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SessionDialog(
    id: Long,
    viewModel: SessionViewModel = hiltViewModel(),
    dismissDialog: () -> (Unit),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(id != state.value.session.id) {
        viewModel.loadData(id)
    }


    if (state.value.isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            ) {
            SessionScreen(
                session = state.value.session,
                dismissDialog = dismissDialog,
                loungeList = state.value.lounges,
                onEvent = { event: SessionEvent -> viewModel.onEvent(event) },
                confirm = {
                    viewModel.postSession()
                    dismissDialog()
                },
            )
        }
    }

}


@Composable
fun SessionScreen(
    session: Session,
    loungeList: List<Lounge>,
    dismissDialog: () -> (Unit),
    onEvent: (SessionEvent) -> (Unit),
    confirm: () -> (Unit),
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SessionScreen(
            session = session,
            onEvent = onEvent
        )
        LoungeRow(
            session = session,
            loungeList = loungeList,
            onEvent = onEvent
        )
        SessionActions(
            confirm = confirm,
            cancel = dismissDialog
        )
    }

}

@Composable
private fun LoungeRow(
    session: Session,
    loungeList: List<Lounge>,
    onEvent: (SessionEvent) -> Unit,
) {

    Row {
        LoungeSelectBox(session = session, loungeList = loungeList, onEvent = onEvent)
    }
}

@Composable
private fun LoungeSelectBox(
    session: Session,
    loungeList: List<Lounge>,
    onEvent: (SessionEvent) -> Unit,
) {

    HookahTextField(
        value = session.lounge.name,
        label = "Lounge",
        width = 0.8f,
        readOnly = true,
        multiline = true,
        keyboardType = KeyboardType.Decimal,
        onValueChange = {}
    )
    var expanded by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.padding(4.dp)) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Expand")
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(0.9f),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            loungeList.forEach { lounge ->
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        expanded = false
                        onEvent(SessionEvent.EnteredLounge(lounge))
                    },
                    enabled = lounge != session.lounge,
                    text = { Text(lounge.name) }
                )
            }
        }
    }
}


@Composable
private fun SessionScreen(
    session: Session,
    onEvent: (SessionEvent) -> (Unit),
) {

    HookahTextField(
        value = session.ownerName,
        label = "Customer name",
        onValueChange = {
            onEvent(SessionEvent.EnteredName(it))
        }
    )

    PhoneRow(session, onEvent)
    DateTimeRow(session, onEvent)
    StatusAccessRow(session, onEvent)

}

@Composable
private fun PhoneRow(session: Session, onEvent: (SessionEvent) -> Unit) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        HookahTextField(
            value = session.ownerCountryCode,
            label = "Country code",
            keyboardType = KeyboardType.Decimal,
            width = 0.3f,
            onValueChange = {
                onEvent(SessionEvent.EnteredCountry(it))
            }
        )

        Spacer(modifier = Modifier.width(4.dp))
        HookahTextField(
            value = session.ownerId,
            label = "Phone",
            keyboardType = KeyboardType.Decimal,
            visualTransformation = remember { PhoneNumberVisualTransformation() },
            onValueChange = {
                onEvent(SessionEvent.EnteredPhone(it))
            }
        )
    }

}

@Composable
private fun DateTimeRow(session: Session, onEvent: (SessionEvent) -> Unit) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        HookahTextField(
            value = session.bookingDate,
            label = "Booking date",
            readOnly = true,
            width = 0.8f,
            keyboardType = KeyboardType.Decimal,
            onValueChange = {}
        )
        Spacer(modifier = Modifier.width(4.dp))
        HookahDateInput(
            onDateConfirm = { dateMillis ->
                val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                val formattedDate = dateFormatter.format(Date(dateMillis))
                onEvent(SessionEvent.EnteredDate(formattedDate))
            },
            onTimeConfirm = { time ->
                onEvent(SessionEvent.EnteredTime(time))
            }
        )
    }
}

@Composable
private fun StatusAccessRow(session: Session, onEvent: (SessionEvent) -> Unit) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        HookahTextField(
            value = session.accessCode,
            label = "Access code",
            width = 0.5f,
            readOnly = true,
            keyboardType = KeyboardType.Decimal,
            onValueChange = {}
        )
        Spacer(modifier = Modifier.width(4.dp))
        HookahTextField(
            value = session.status,
            label = "Status",
            width = 0.6f,
            readOnly = true,
            keyboardType = KeyboardType.Decimal,
            onValueChange = {}
        )
        HookahSelectBox(
            options = SessionUiState.getStatusList().map {
                it.value
            },
            currentValue = session.status,
            getter = { status: String -> status },
            onEvent = { event: String -> onEvent(SessionEvent.EnteredStatus(event)) }
        )
    }

}

@Composable
private fun SessionActions(
    confirm: () -> (Unit),
    cancel: () -> (Unit),
) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ElevatedButton(modifier = Modifier.fillMaxWidth(0.5f), onClick = cancel) {
            TitleSmall(text = "Cancel", align = TextAlign.Center)
        }

        Spacer(Modifier.fillMaxWidth(0.1f))

        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { confirm() }) {
            TitleSmall(text = "Ok", align = TextAlign.Center)
        }
    }

}
