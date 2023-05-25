package com.example.hookahlounge.ui.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.model.Order
import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.domain.model.Table
import com.example.hookahlounge.domain.model.User
import com.example.hookahlounge.ui.theme.HookahLoungeTheme
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahScaffold
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahTextField
import com.example.hookahlounge.ui.theme.hookah_ui_elements.ToggleableText


@Composable
fun OrderScreen() {
    val order = Order(
        id = 1L,
        table = Table(3L, "Table 3", 4, 1L),
        session = Session(
            accessCode = "SR2222",
            id = 1L,
            owner = User(name = "Alexander Vasilenko", id = 1L, phone = "+380684432269"),
            status = false,
            lounge = Lounge(1L, "BadSide", "вулиця Лермонтова, 37, Кривий Ріг,"),
            lockDate = "20230-03-25Z17:00"
        ),
        sum = 0.0,
    )
    HookahScaffold {
        OrderScreen(order)
    }
}

@Composable
private fun OrderScreen(order: Order) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        HookahTextField(value = order.table.name, label = "Table", onValueChange = { it })

        HookahTextField(value = order.session.lockDate, label = "Date", onValueChange = { it })
        var state by remember { mutableStateOf(0) }
        val titles = listOf("Tab 1", "Tab 2")

        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title) }
                )
            }
        }

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Text tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
        if (state == 0) {
            OrderFoodTab()
        } else {
            OrderHookahTab()
        }
    }
}

@Composable
fun OrderFoodTab() {
    ToggleableText(text = "Text tab selected") {
        Text(
            text = "Text tab 1 shown",
            style = MaterialTheme.typography.bodyLarge
        )
    }

    ToggleableText(text = "Text tab selected") {
        Text(
            text = "Text tab 1 shown",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun OrderHookahTab() {
    ToggleableText(text = "Text tab 2 selected") {
        Text(
            text = "Text tab  shown",
            style = MaterialTheme.typography.bodyLarge
        )
    }

    ToggleableText(text = "Text tab 2 selected") {
        Text(
            text = "Text tab  shown",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OrderScreenPreview() {
    HookahLoungeTheme {
        OrderScreen()
    }
}
