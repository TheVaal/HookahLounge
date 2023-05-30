package com.example.hookahlounge.ui.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hookahlounge.R
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.model.Order
import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.domain.model.Table
import com.example.hookahlounge.domain.model.User
import com.example.hookahlounge.ui.theme.HookahLoungeTheme
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HeadlineLarge
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahLazyColumn
import com.example.hookahlounge.ui.theme.hookah_ui_elements.TitleLarge

@Composable
fun OrderListScreen(toOrder: () -> Unit) {
    val list: List<Order> = listOf(
        Order(
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
        ),
        Order(
            id = 2L,
            table = Table(2L, "Table 2", 2, 1L),
            session = Session(
                accessCode = "RT6922",
                id = 2L,
                owner = User(name = "Valerii Rozier III", id = 2L, phone = "+380686632269"),
                status = false,
                lounge = Lounge(1L, "BadSide", "вулиця Лермонтова, 37, Кривий Ріг,"),
                lockDate = "20230-03-25Z17:00"
            ),
            sum = 0.0,
            closed = true
        )

    )
    OrderListScreen(list = list, toOrder = toOrder)

}

@Composable
private fun OrderListScreen(list: List<Order>, toOrder: () -> Unit){
    HookahLazyColumn(items = list) {
        OrderContent(it, toOrder)
    }
}

@Composable
private fun OrderContent(item: Order, toOrder: () -> Unit) {
    TextButton(onClick = toOrder) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp).fillMaxWidth(0.8f)
            ) {
                HeadlineLarge(
                    text = "#${item.id} ${item.table?.name}",
                    fontWeight = FontWeight(1000)
                )
                item.session?.lounge?.let {
                    TitleLarge(
                        text = it.name,
                        fontWeight = FontWeight(500)
                    )
                }
                item.session?.let {
                    TitleLarge(
                        text = it.lockDate,
                        fontWeight = FontWeight(500)
                    )
                }
            }
            Icon(
                contentDescription = null,
                painter = getOrderStatusIcon(item.closed),
                modifier = Modifier.fillMaxHeight().fillMaxWidth(0.4f)
            )
        }
    }
}

@Composable
fun getOrderStatusIcon(status:Boolean): Painter {
    return if (status) {
        painterResource(id = R.drawable.ic_lock_24)
    } else {
        painterResource(id = R.drawable.ic_lock_open_24)
    }
}

@Preview(showBackground = true)
@Composable
fun OrderListPreview() {
    HookahLoungeTheme {
        OrderListScreen {  }
    }
}