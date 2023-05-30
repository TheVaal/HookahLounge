package com.example.hookahlounge.ui.navigationdrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hookahlounge.util.BaseNavigator

data class MenuItem(
    val title: String = "",
    val contentDescription: String = "",
    val id: String = "",
    val icon: ImageVector,
    val action: ()->(Unit),
)
{
    companion object{
        fun DrawerMenu(navigator: BaseNavigator):List<MenuItem>{
            return listOf(
                MenuItem(
                    id = "order",
                    title =  "Orders",
                    icon = Icons.Filled.Place,
                    contentDescription = "List of orders",
                    action = { navigator.toOrders() }
                ),
                MenuItem(
                    id = "session",
                    title =  "Sessions",
                    icon = Icons.Filled.AccountBox,
                    contentDescription = "List of sessions",
                    action = { navigator.toSessions() }
                ),
                MenuItem(
                    id = "lounge",
                    title =  "Lounges",
                    icon = Icons.Filled.Home,

                    contentDescription = "List of lounges",
                    action = { navigator.toLounges() }
                ),
            )
        }
    }
}
