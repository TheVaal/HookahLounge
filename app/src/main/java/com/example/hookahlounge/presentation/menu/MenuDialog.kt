package com.example.hookahlounge.presentation.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hookahlounge.domain.model.LoungeMenu
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahTextField
import com.example.hookahlounge.presentation.hookah_ui_elements.TitleSmall
import com.example.hookahlounge.presentation.menu.viewmodel.single.LoungeMenuEvent
import com.example.hookahlounge.presentation.menu.viewmodel.single.LoungeMenuViewModel

@Composable
fun MenuDialog(
    loungeId: Long,
    menuId: Long,
    loungeMenuId: Long,
    viewModel: LoungeMenuViewModel = hiltViewModel(),
    dismissDialog: () -> (Unit),
) {
    LaunchedEffect(true) {
        viewModel.loadData(
            loungeId = loungeId,
            menuId = menuId,
            loungeMenuId = loungeMenuId
        )
    }

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.value.isLoading) {
            CircularProgressIndicator()
        } else {
            DialogContent(
                loungeMenu = state.value.loungeMenu,
                onEvent = { event: LoungeMenuEvent ->
                    viewModel.onEvent(event)
                },
                onDismiss = dismissDialog,
                onConfirm = {
                    viewModel.postMenu()
                    dismissDialog()
                }
            )
        }
    }

}

@Composable
private fun DialogContent(
    loungeMenu: LoungeMenu,
    onEvent: (LoungeMenuEvent) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    DialogFields(loungeMenu, onEvent)
    DialogActions(onConfirm, onDismiss)
}

@Composable
private fun DialogFields(loungeMenu: LoungeMenu, onEvent: (LoungeMenuEvent) -> Unit) {
    HookahTextField(value = loungeMenu.menu.name,
        label = "Name",
        onValueChange = {
            onEvent(LoungeMenuEvent.EnteredName(it))
        }
    )
    HookahTextField(
        value = loungeMenu.price,
        label = "price",
        keyboardType = KeyboardType.Decimal,
        onValueChange = {
            onEvent(LoungeMenuEvent.EnteredPrice(it))
        }
    )
}

@Composable
private fun DialogActions(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ElevatedButton(modifier = Modifier.fillMaxWidth(0.5f), onClick = onDismiss) {
            TitleSmall(text = "Cancel", align = TextAlign.Center)
        }

        Spacer(Modifier.fillMaxWidth(0.1f))

        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onConfirm() }) {
            TitleSmall(text = "Ok", align = TextAlign.Center)
        }
    }
}