package com.example.hookahlounge.ui.theme.hookah_ui_elements

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import com.example.hookahlounge.R

@Composable
fun TitleLarge(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TitleMedium(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TitleSmall(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun HeadlineLarge(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun HeadlineMedium(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun HeadlineSmall(text: String, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ToggleableText(text: String, contentParam: Any? = null, content: @Composable (Any?) -> (Unit)) {
    var state by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = state,
                enabled = true,
                role = Role.Button,
                onValueChange = {
                    state = it
                }
            ),
        contentColor = MaterialTheme.colorScheme.onPrimary,
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 8.dp
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(

                text = text,
                style = MaterialTheme.typography.titleMedium,
            )
            Icon(
                getToggleableIcon(state),
                contentDescription = "newIssue",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

    }
    if (state) {
        content(contentParam)
    }

}

@Composable
private fun getToggleableIcon(state:Boolean): ImageVector {
    return if (state) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
}