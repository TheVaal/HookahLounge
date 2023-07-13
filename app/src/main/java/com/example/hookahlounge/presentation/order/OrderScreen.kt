package com.example.hookahlounge.presentation.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hookahlounge.domain.model.InOrder
import com.example.hookahlounge.domain.model.LoungeMenu
import com.example.hookahlounge.domain.model.LoungeTobacco
import com.example.hookahlounge.domain.model.Mix
import com.example.hookahlounge.domain.model.Order
import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.domain.model.Table
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahLazyColumn
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahSelectBox
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahTextField
import com.example.hookahlounge.presentation.hookah_ui_elements.TitleMedium
import com.example.hookahlounge.presentation.hookah_ui_elements.ToggleableText
import com.example.hookahlounge.presentation.order.viewmodel.OrderStatus
import com.example.hookahlounge.presentation.order.viewmodel.OrderUIEvent
import com.example.hookahlounge.presentation.order.viewmodel.OrderUiState
import com.example.hookahlounge.ui.theme.HookahLoungeTheme


@Composable
fun OrderScreen(uiState: OrderUiState, onEvent: (OrderUIEvent) -> (Unit)) {
    val order = uiState.order
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("Food", "Hookah")
    HookahLazyColumn(
        items = if (state == 0) {
            (1..uiState.guests.toInt()).toList()
        } else {
            listOf(1)
        },
        header = {
            state = orderHeader(
                order = order,
                state = state,
                guests = uiState.guests,
                sessions = uiState.sessions,
                tables = uiState.lounge.tables,
                titles = titles,
                onEvent = onEvent
            )
        },
        wrapContent = false
    ) {

        if (state == 0) {
            OrderFoodTab(
                inOrder = uiState.inOrder,
                guest = it,
                loungeMenu = uiState.lounge.menu,
                onEvent = onEvent
            )
        } else {
            OrderHookahTab(
                mixes = uiState.mixes,
                tobaccos = uiState.lounge.tobacco,
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun OrderHookahTab(
    mixes: List<Mix>,
    tobaccos: List<LoungeTobacco>,
    onEvent: (OrderUIEvent) -> Unit,
) {
    mixes.forEach { mix ->
        OrderHookahTabItem(mix = mix, tobaccos = tobaccos, onEvent = onEvent)
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(onClick = { onEvent(OrderUIEvent.AddMix) }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Post/Edit mix",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun orderHeader(
    order: Order,
    state: Int,
    guests: String,
    tables: List<Table>,
    sessions: List<Session>,
    titles: List<String>,
    onEvent: (OrderUIEvent) -> (Unit),
): Int {
    var newState by remember { mutableIntStateOf(state) }
    TableSelectBox(
        table = order.table,
        tables = tables,
        onSelect = { onEvent(OrderUIEvent.SelectedTable(it)) }
    )
    SessionSelectBox(
        session = order.session,
        sessions = sessions,
        onSelect = { onEvent(OrderUIEvent.SelectedSession(it)) }
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OrderStatusSelectBox(order.status) {
            onEvent(OrderUIEvent.ChangeStatus(it))
        }

        OrderGuestsSelectBox(guests.toInt()) {
            onEvent(OrderUIEvent.ChangeGuests(it.toString()))
        }
        HookahTextField(
            value = order.sum.toString(),
            label = "Sum",
            width = 0.9f,
            readOnly = true,
            onValueChange = {})
    }
    TabRow(selectedTabIndex = newState) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = newState == index,
                onClick = { newState = index },
                text = { Text(text = title) }
            )
        }
    }
    return newState
}

@Composable
private fun SessionSelectBox(
    session: Session,
    sessions: List<Session>,
    onSelect: (Session) -> (Unit),
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        HookahTextField(
            value = session.bookingDate,
            label = "Date",
            readOnly = true,
            onValueChange = { }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            HookahSelectBox(
                options = sessions,
                currentValue = session,
                menuWidth = 0.75f,
                getter = { value: Session ->
                    "${value.bookingDate}\n${value.ownerName} " +
                            value.ownerId
                },
                onEvent = { value: Session ->
                    onSelect(value)
                }
            )
        }
    }
}

@Composable
fun OrderGuestsSelectBox(guests: Int, onSelect: (Int) -> (Unit)) {
    val list = (guests..guests.plus(10)).toList()
    Box(modifier = Modifier.fillMaxWidth(0.4f)) {
        HookahTextField(
            value = guests.toString(),
            label = "Guests",
            readOnly = true,
            keyboardType = KeyboardType.Decimal,
            onValueChange = {}
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            HookahSelectBox(
                options = list,
                currentValue = guests,
                width = 0.5f,
                getter = { value: Int -> value.toString() },
                onEvent = { value: Int ->
                    onSelect(value)
                }
            )
        }
    }
}

@Composable
private fun TableSelectBox(
    table: Table,
    tables: List<Table>,
    onSelect: (Table) -> (Unit),
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        HookahTextField(
            value = table.name,
            label = "Menu",
            readOnly = true,
            onValueChange = {}
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            HookahSelectBox(
                options = tables,
                currentValue = table,
                width = 0.5f,
                getter = { value: Table -> value.name },
                onEvent = { value: Table ->
                    onSelect(value)
                }
            )
        }
    }
}

@Composable
private fun OrderStatusSelectBox(status: String, onSelect: (String) -> (Unit)) {
    Box(modifier = Modifier.fillMaxWidth(0.3f)) {
        HookahTextField(
            value = status,
            label = "Status",
            readOnly = true,
            onValueChange = {})
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            HookahSelectBox(
                options = OrderStatus.values().toList().map { it.value },
                currentValue = status,
                width = 0.5f,
                getter = { it }
            ) {
                onSelect(it)
            }
        }

    }
}

@Composable
private fun OrderFoodTab(
    inOrder: List<InOrder>,
    guest: Int,
    loungeMenu: List<LoungeMenu>,
    onEvent: (OrderUIEvent) -> (Unit),
) {
    Spacer(modifier = Modifier.height(4.dp))
    ToggleableText(text = "Guest $guest") {
        inOrder.filter { guest == it.guest }.forEach { item ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TitleMedium(text = item.menu.menu.name, width = 0.5f)
                Spacer(modifier = Modifier.width(4.dp))
                HookahTextField(
                    value = item.quantity,
                    label = "Quantity",
                    width = 0.5f,
                    keyboardType = KeyboardType.Number,
                    onValueChange = { value ->
                        onEvent(
                            OrderUIEvent.ChangeQuantity(
                                item,
                                value
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.width(4.dp))
                HookahTextField(
                    value = item.menu.price,
                    label = "Price",
                    onValueChange = {},
                    readOnly = true,
                )

            }
        }
        AddInOrderRow(guest = guest, loungeMenu = loungeMenu, onEvent = onEvent)
    }
}

@Composable
private fun AddInOrderRow(
    guest: Int,
    loungeMenu: List<LoungeMenu>,
    onEvent: (OrderUIEvent) -> (Unit),
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var menu by remember { mutableStateOf(LoungeMenu()) }
        var quantity by remember { mutableStateOf("0.0") }
        MenuSelectBox(menu, loungeMenu) { value -> menu = value }
        Spacer(modifier = Modifier.width(4.dp))
        HookahTextField(
            value = quantity,
            label = "Quantity",
            width = 0.5f,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                quantity = it
            }
        )
        Spacer(modifier = Modifier.width(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.Center),
                onClick = {
                    onEvent(
                        OrderUIEvent.AddInOrder(
                            menu = menu,
                            guest = guest,
                            quantity = quantity.toDouble()
                        )
                    )
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Expand")
            }
        }
    }

}

@Composable
private fun MenuSelectBox(
    menu: LoungeMenu,
    loungeMenu: List<LoungeMenu>,
    onSelect: (LoungeMenu) -> (Unit),
) {
    Box(modifier = Modifier.fillMaxWidth(0.5f)) {
        HookahTextField(
            value = menu.menu.name,
            label = "Menu",
            readOnly = true,
            onValueChange = {}
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            HookahSelectBox(
                options = loungeMenu,
                currentValue = menu,
                width = 0.3f,
                getter = { value: LoungeMenu -> value.menu.name },
                onEvent = { value: LoungeMenu ->
                    onSelect(value)
                }
            )
        }

    }
}

@Composable
private fun TobaccoSelectBox(
    tobacco: LoungeTobacco,
    tobaccos: List<LoungeTobacco>,
    onSelect: (LoungeTobacco) -> (Unit),
) {
    Box(modifier = Modifier.fillMaxWidth(0.5f)) {
        HookahTextField(
            value = "${tobacco.tobacco.manufacturer.name} ${tobacco.tobacco.taste}",
            label = "Menu",
            readOnly = true,
            onValueChange = {}
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            HookahSelectBox(
                options = tobaccos,
                currentValue = tobacco,
                width = 0.3f,
                getter = { value: LoungeTobacco ->
                    "${value.tobacco.manufacturer.name} ${value.tobacco.taste}"
                },
                onEvent = { value: LoungeTobacco ->
                    onSelect(value)
                }
            )
        }
    }
}

@Composable
private fun OrderHookahTabItem(
    mix: Mix,
    tobaccos: List<LoungeTobacco>,
    onEvent: (OrderUIEvent) -> (Unit),
) {
    val title = mix.hookah.joinToString { it.loungeTobacco.tobacco.taste }
    Spacer(modifier = Modifier.height(4.dp))
    ToggleableText(text = title) {
        mix.hookah.forEach {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TitleMedium(
                    text = "${it.loungeTobacco.tobacco.manufacturer.name} ${it.loungeTobacco.tobacco.taste}",
                    width = 0.5f
                )
                HookahTextField(
                    value = it.loungeTobacco.price,
                    label = "Price",
                    onValueChange = {},
                    readOnly = true,
                    width = 0.5f
                )
                Spacer(modifier = Modifier.width(4.dp))
                HookahTextField(
                    value = it.weight,
                    label = "Weight",
                    keyboardType = KeyboardType.Number,
                    onValueChange = {},
                    readOnly = true,
                )
            }
        }
        AddHookahRow(
            mixId = mix.id,
            loungeTobacco = tobaccos,
            onEvent = onEvent
        )
    }
}

@Composable
private fun AddHookahRow(
    mixId: Long,
    loungeTobacco: List<LoungeTobacco>,
    onEvent: (OrderUIEvent) -> (Unit),
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var tobacco by remember { mutableStateOf(LoungeTobacco()) }
        var weight by remember { mutableStateOf("0.0") }
        TobaccoSelectBox(tobacco, loungeTobacco) { value -> tobacco = value }
        Spacer(modifier = Modifier.width(4.dp))
        HookahTextField(
            value = weight,
            label = "Weight",
            width = 0.5f,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                weight = it
            }
        )
        Spacer(modifier = Modifier.width(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.Center),
                onClick = {
                    onEvent(
                        OrderUIEvent.AddHookah(
                            tobacco = tobacco,
                            mixId = mixId,
                            weight = weight
                        )
                    )
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Expand")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OrderScreenPreview() {
    HookahLoungeTheme {

    }
}
