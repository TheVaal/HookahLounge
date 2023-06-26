package com.example.hookahlounge.presentation.tobacco

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
import com.example.hookahlounge.domain.model.Hardness
import com.example.hookahlounge.domain.model.LoungeTobacco
import com.example.hookahlounge.domain.model.Manufacturer
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahSelectBox
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahTextField
import com.example.hookahlounge.presentation.hookah_ui_elements.TitleSmall
import com.example.hookahlounge.presentation.tobacco.viewmodel.single.LoungeTobaccoEvent
import com.example.hookahlounge.presentation.tobacco.viewmodel.single.TobaccoViewModel

@Composable
fun TobaccoDialog(
    loungeTobaccoId: Long,
    tobaccoId: Long,
    loungeId: Long,
    viewModel: TobaccoViewModel = hiltViewModel(),
    dismissDialog: () -> Unit,
) {
    LaunchedEffect(true) {
        viewModel.loadData(
            loungeId = loungeId,
            tobaccoId = tobaccoId,
            loungeTobaccoId = loungeTobaccoId
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
                loungeTobacco = state.value.loungeTobacco,
                hardnessList = state.value.hardnessList,
                manufacturers = state.value.manufacturers,
                onEvent = { event: LoungeTobaccoEvent ->
                    viewModel.onEvent(event)
                },
                onDismiss = dismissDialog,
                onConfirm = {
                    viewModel.postTobacco()
                    dismissDialog()
                }
            )
        }
    }
}

@Composable
private fun DialogContent(
    loungeTobacco: LoungeTobacco,
    hardnessList: List<Hardness>,
    manufacturers: List<Manufacturer>,
    onEvent: (LoungeTobaccoEvent) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    DialogFields(
        loungeTobacco = loungeTobacco,
        hardnessList = hardnessList,
        manufacturers = manufacturers,
        onEvent = onEvent
    )
    DialogActions(onConfirm, onDismiss)
}

@Composable
private fun DialogFields(
    loungeTobacco: LoungeTobacco,
    hardnessList: List<Hardness>,
    manufacturers: List<Manufacturer>,
    onEvent: (LoungeTobaccoEvent) -> Unit,
) {
    Row(Modifier.fillMaxWidth()) {
        HookahTextField(value = loungeTobacco.tobacco.manufacturer.name,
            label = "Manufacturer",
            width = 0.8f,
            onValueChange = {
                onEvent(LoungeTobaccoEvent.EnteredManufacturerName(it))
            }
        )
        HookahSelectBox(
            options = manufacturers,
            currentValue = loungeTobacco.tobacco.manufacturer,
            getter = { value: Manufacturer -> value.name },
            onEvent = { value: Manufacturer ->
                onEvent(
                    LoungeTobaccoEvent.SelectedManufacturerName(
                        value
                    )
                )
            })
    }

    Row(Modifier.fillMaxWidth()) {
        HookahTextField(value = loungeTobacco.tobacco.hardness.name,
            label = "Hardness",
            width = 0.8f,
            readOnly = true,
            onValueChange = {}
        )
        HookahSelectBox(
            options = hardnessList,
            currentValue = loungeTobacco.tobacco.hardness,
            getter = { value: Hardness -> value.name },
            onEvent = { value: Hardness -> onEvent(LoungeTobaccoEvent.SelectedHardness(value)) })
    }
    HookahTextField(
        value = loungeTobacco.tobacco.taste,
        label = "Taste",
        keyboardType = KeyboardType.Decimal,
        onValueChange = {
            onEvent(LoungeTobaccoEvent.EnteredTaste(it))
        }
    )
    HookahTextField(
        value = loungeTobacco.price,
        label = "Price",
        keyboardType = KeyboardType.Decimal,
        onValueChange = {
            onEvent(LoungeTobaccoEvent.EnteredPrice(it))
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