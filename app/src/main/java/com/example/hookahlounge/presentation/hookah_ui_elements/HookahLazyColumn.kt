package com.example.hookahlounge.presentation.hookah_ui_elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T>HookahLazyColumn(items: List<T>, content: @Composable (T) -> (Unit)) {
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
fun <T : Any>HookahLazyColumn(items: LazyPagingItems<T>, content: @Composable (T) -> (Unit)) {
    LazyColumn {
        items(
            count = items.itemCount,
        ) { index ->
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
                val item = items[index]
                if (item != null) {
                    content(item)
                }
            }
        }
    }
}
@Composable
fun <T>HookahLazyGrid(items: List<T>, gridItem: @Composable () -> (Unit) = {}, content: @Composable (T) -> (Unit)) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column(Modifier.fillMaxWidth()) {
                gridItem()
            }
        }
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