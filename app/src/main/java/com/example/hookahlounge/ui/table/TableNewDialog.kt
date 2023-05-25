package com.example.hookahlounge.ui.table

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahScaffold
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahTextField

@Composable
fun TableNewScreen() {

    HookahScaffold {
        TableScreen()
    }
}

@Composable
private fun TableScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        HookahTextField(value = "", label = "Name", onValueChange = { it })
        HookahTextField(value = "", label = "Seats", onValueChange = { it })
        HookahTextField(value = "", label = "Lounge", onValueChange = { it }) // TODO SelectLounge
    }
}