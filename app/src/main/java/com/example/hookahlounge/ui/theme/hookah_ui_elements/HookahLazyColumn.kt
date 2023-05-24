package com.example.hookahlounge.ui.theme.hookah_ui_elements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HookahLazyColumn(items: List<Any>, content: @Composable (Any) -> (Unit)) {
    LazyColumn {
        items(items = items) { item ->
            OutlinedCard(
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 16.dp,
                    pressedElevation = 16.dp,
                    focusedElevation = 16.dp,
                    hoveredElevation = 16.dp,
                    draggedElevation = 16.dp,
                    disabledElevation = 16.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            ) {
                content(item)
            }
        }
    }
}

@Composable
fun HookahLazyGrid(items: List<Any>, content: @Composable (Any) -> (Unit)) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(items = items) { item ->
            OutlinedCard(
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 16.dp,
                    pressedElevation = 16.dp,
                    focusedElevation = 16.dp,
                    hoveredElevation = 16.dp,
                    draggedElevation = 16.dp,
                    disabledElevation = 16.dp
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)

            ) {
                content(item)
            }
        }
    }
}
