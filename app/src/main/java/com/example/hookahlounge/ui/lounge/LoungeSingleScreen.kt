package com.example.hookahlounge.ui.lounge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.model.Table
import com.example.hookahlounge.ui.theme.HookahLoungeTheme
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HeadlineMedium
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HeadlineSmall
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahLazyGrid
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahScaffold
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahTextField

@Composable
fun LoungeScreen() {

    val lounge = Lounge(
        name = "BadSide",
        id = 1L,
        address = "вулиця Лермонтова, 37, Кривий Ріг,"
    )
    val tables: List<Table> = listOf(
        Table(1L, "Table 1", 4, 1L),
        Table(2L, "Table 2", 2, 1L),
        Table(3L, "Table 3", 4, 1L),
        Table(4L, "Table 4", 2, 1L),
        Table(5L, "Table 5", 6, 1L),
        Table(6L, "Table 6", 2, 1L)
    )
    HookahScaffold {
        LoungeScreen(lounge, tables)
    }

}

@Composable
private fun LoungeScreen(lounge: Lounge, tables: List<Table>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LoungeZone(lounge = lounge)
        TableZone(tables)
    }
}

@Composable
private fun LoungeZone(lounge: Lounge) {
    HookahTextField(
        value = lounge.name,
        onValueChange = { lounge.copy(name = it) },
        label = "name"
    )

    HookahTextField(
        value = lounge.address,
        label = "address",
        multiline = true,
        onValueChange = { lounge.copy(address = it) },
    )
}

@Composable
private fun TableZone(tables: List<Table>) {
    HookahLazyGrid(items = tables) {
        TableContent(it as Table)
    }
}

@Composable
private fun TableContent(table: Table) {
    TextButton(onClick = { /*TODO*/ }) {
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
        LoungeScreen()
    }
}