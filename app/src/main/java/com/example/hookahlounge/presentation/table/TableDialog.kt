package com.example.hookahlounge.presentation.table

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hookahlounge.domain.model.Table
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahTextField
import com.example.hookahlounge.presentation.hookah_ui_elements.TitleSmall
import com.example.hookahlounge.presentation.table.viewmodel.TableEvent
import com.example.hookahlounge.presentation.table.viewmodel.TableViewModel

@Composable
fun TableDialog(
    id: Long?,
    loungeId: Long?,
    viewModel: TableViewModel = hiltViewModel(),
    dismissDialog: () -> (Unit),
) {
    LaunchedEffect(true) {
        viewModel.loadData(id, loungeId)
    }
    val state = viewModel.uiState.collectAsStateWithLifecycle()
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
            TableDialogScreen(
                table = state.value.table,
                confirm = {
                    viewModel.postTable()
                    dismissDialog()
                },
                cancel = dismissDialog,
                onEvent = { event: TableEvent -> viewModel.onEvent(event) }
            )
        }
    }
}

@Composable
private fun TableDialogScreen(
    table: Table,
    confirm: () -> (Unit),
    cancel: () -> (Unit),
    onEvent: (TableEvent) -> (Unit),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        HookahTextField(value = table.name,
            label = "Name",
            onValueChange = {
                onEvent(TableEvent.EnteredName(it))
            }
        )
        HookahTextField(
            value = table.seats,
            label = "Seats",
            keyboardType = KeyboardType.Decimal,
            onValueChange = {
                onEvent(TableEvent.EnteredSize(it))
            }
        )
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
}