package com.example.hookahlounge.ui.navigationdrawer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hookahlounge.domain.model.User
import com.example.hookahlounge.ui.theme.hookah_ui_elements.TitleMedium


@Composable
fun NavigationDrawer(items: List<MenuItem>) {
    val currentUser: User = User(
        login = "vaal",
        id = 1L,
        name = "Valerii Krivoruchko",
        phone = "+380684181893"
    )
    NavigationDrawerHeader()
    NavigationDrawerBody(items)
}

@Composable
private fun NavigationDrawerHeader() {


}

@Composable
private fun NavigationDrawerBody(items: List<MenuItem>) {
    Surface(
        Modifier
            .fillMaxWidth(0.4f)
            .fillMaxHeight()
    ) {
        LazyColumn {
            items(items = items) { item ->
                NavigationDrawerContent(item)
            }
        }
    }
}

@Composable
private fun NavigationDrawerContent(item: MenuItem) {
    Surface(onClick = item.action) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(imageVector = item.icon, contentDescription = item.contentDescription)
            Spacer(Modifier.width(16.dp))
            TitleMedium(text = item.title, fontWeight = FontWeight(1000))

        }
    }
}