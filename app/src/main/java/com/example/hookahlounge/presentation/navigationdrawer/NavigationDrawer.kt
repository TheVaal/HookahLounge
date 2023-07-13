package com.example.hookahlounge.presentation.navigationdrawer

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.hookahlounge.domain.model.User
import com.example.hookahlounge.presentation.hookah_ui_elements.TitleMedium


@Composable
fun NavigationDrawer(items: List<MenuItem>, currentUser: User) {

    ModalDrawerSheet(
        Modifier
            .fillMaxWidth(getDrawerMaxSize(LocalConfiguration.current))
            .fillMaxHeight().padding(top = 32.dp)
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                NavigationDrawerHeader(currentUser)
                Spacer(modifier = Modifier.height(32.dp))
                NavigationDrawerBody(items)
            }
        }

    }
}

fun getDrawerMaxSize(configuration: Configuration): Float {
    return when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            0.3f
        }

        else -> {
            0.6f
        }
    }
}

@Composable
private fun NavigationDrawerHeader(currentUser: User) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Filled.Person, contentDescription = "Use name")
        TitleMedium(text = currentUser.login, FontWeight.Bold)
    }
}

@Composable
private fun NavigationDrawerBody(items: List<MenuItem>) {
    LazyColumn {
        items(items = items) { item ->
            NavigationDrawerContent(item)
        }
    }
}

@Composable
private fun NavigationDrawerContent(item: MenuItem) {
    Surface(onClick = item.action) {
        Row {
            Icon(imageVector = item.icon, contentDescription = item.contentDescription)
            Spacer(Modifier.width(16.dp))
            TitleMedium(text = item.title, fontWeight = FontWeight(1000))

        }
    }
}