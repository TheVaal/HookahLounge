package com.example.hookahlounge.ui.theme.hookah_ui_elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TitleLarge(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TitleMedium(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TitleSmall(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun HeadlineLarge(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun HeadlineMedium(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun HeadlineSmall(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}