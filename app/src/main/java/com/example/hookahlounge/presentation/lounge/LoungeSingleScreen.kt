package com.example.hookahlounge.presentation.lounge


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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.model.Table
import com.example.hookahlounge.presentation.hookah_ui_elements.HeadlineMedium
import com.example.hookahlounge.presentation.hookah_ui_elements.HeadlineSmall
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahLazyGrid
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahTextField
import com.example.hookahlounge.presentation.lounge.viewmodel.LoungeEvent
import com.example.hookahlounge.presentation.lounge.viewmodel.LoungeUiState
import com.example.hookahlounge.ui.theme.HookahLoungeTheme

@Composable
fun LoungeScreen(
    state: LoungeUiState,
    toTable: (Long) -> (Unit),
    onEvent: (LoungeEvent) -> (Unit),
) {

    LoungeScreen(
        state.lounge,
        state.isLoading,
        toTable,
        onEvent
    )
}

@Composable
private fun LoungeScreen(
    lounge: Lounge,
    isLoading: Boolean,
    toTable: (Long) -> (Unit),
    onEvent: (LoungeEvent) -> (Unit),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (isLoading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

        } else {

            Spacer(Modifier.height(16.dp))
            HookahLazyGrid(
                items = lounge.tables,
                gridItem = {
                    LoungeZone(
                        lounge = lounge,
                        onEvent = onEvent
                    )
                }
            ) {
                TableContent(it, toTable)
            }

        }
    }

}

@Composable
private fun LoungeZone(
    lounge: Lounge,
    onEvent: (LoungeEvent) -> (Unit),
) {

    HookahTextField(
        value = lounge.name,
        onValueChange = { value ->
            onEvent(LoungeEvent.EnteredName(value))
        },
        label = "name"
    )

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Box(Modifier.fillMaxWidth(0.4f)) {
            HookahTextField(
                value = lounge.postalCode,
                label = "postalCode",
                onValueChange = {
                    onEvent(LoungeEvent.EnteredPostalCode(it))
                },
            )
        }
        Spacer(Modifier.width(4.dp))
        Box(Modifier.fillMaxWidth()) {
            HookahTextField(
                value = lounge.country,
                label = "country",
                onValueChange = {
                    onEvent(LoungeEvent.EnteredCountry(it))
                },
            )
        }
    }

    HookahTextField(
        value = lounge.state,
        label = "state",
        onValueChange = {
            onEvent(LoungeEvent.EnteredState(it))
        },
    )

    HookahTextField(
        value = lounge.city,
        label = "city",
        onValueChange = {
            onEvent(LoungeEvent.EnteredCity(it))
        },
    )

    HookahTextField(
        value = lounge.address,
        label = "address",
        multiline = true,
        onValueChange = {
            onEvent(LoungeEvent.EnteredAddress(it))
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TableContent(
    table: Table,
    toTable: (Long) -> (Unit),
) {
    ElevatedCard(onClick = { toTable(table.id) }) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            HeadlineMedium(
                text = table.name,
                fontWeight = FontWeight(1000)
            )
            HeadlineSmall(
                text = "Size: ${table.seats}",
                fontWeight = FontWeight(1000)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoungeScreenPreview() {
    HookahLoungeTheme {

    }
}