package com.example.hookahlounge.presentation.order

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.hookahlounge.R
import com.example.hookahlounge.domain.model.Order
import com.example.hookahlounge.ui.theme.HookahLoungeTheme
import com.example.hookahlounge.presentation.hookah_ui_elements.HeadlineLarge
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahLazyColumn
import com.example.hookahlounge.presentation.hookah_ui_elements.TitleLarge
import com.example.hookahlounge.presentation.order.viewmodel.OrderListViewModel

@Composable
fun OrderListScreen(toOrder: (Long) -> Unit, viewModel: OrderListViewModel = hiltViewModel()) {

    val orders = viewModel.loungePagingFlow.collectAsLazyPagingItems()

    OrderListScreen(orders = orders, toOrder = toOrder)

}

@Composable
private fun OrderListScreen(list: List<Order>, toOrder: (Long) -> Unit){
    HookahLazyColumn(items = list) {
        OrderContent(it, toOrder)
    }
}

@Composable
private fun OrderListScreen(orders: LazyPagingItems<Order>, toOrder: (Long) -> (Unit)) {
    val context = LocalContext.current
    LaunchedEffect(key1 = orders.loadState) {
        if (orders.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (orders.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally ) {
        if (orders.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator()
        } else {
            HookahLazyColumn(items = orders) {
                OrderContent(it, toOrder)
            }
        }
        if (orders.loadState.append == LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OrderContent(item: Order, toOrder: (Long) -> Unit) {
    ElevatedCard(
        modifier = Modifier
        .fillMaxWidth(),
        onClick = { toOrder(item.id) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
            ) {
                HeadlineLarge(
                    text = "#${item.id} ${item.table.name}",
                    fontWeight = FontWeight(1000)
                )
                item.session.lounge.let {
                    TitleLarge(
                        text = it.name,
                        fontWeight = FontWeight(500)
                    )
                }
                item.session.let {
                    TitleLarge(
                        text = it.bookingDate,
                        fontWeight = FontWeight(500)
                    )
                }
            }
            Icon(
                contentDescription = null,
                painter = getOrderStatusIcon(item.closed),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.4f)
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
        OrderListScreen(listOf()) {  }
    }
}