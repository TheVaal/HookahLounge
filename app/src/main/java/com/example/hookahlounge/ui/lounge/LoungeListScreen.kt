package com.example.hookahlounge.ui.lounge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.ui.theme.HookahLoungeTheme
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HeadlineLarge
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahLazyColumn
import com.example.hookahlounge.ui.theme.hookah_ui_elements.TitleMedium


@Composable
fun LoungeListScreen(toLounge: ()->(Unit)) {
    val list: List<Lounge> = listOf(
        Lounge(
            name = "BadSide",
            id = 1L,
            address = "вулиця Лермонтова, 37, Кривий Ріг,"
        ),
        Lounge(
            name = "BadSide black",
            id = 2L,
            address = "вулиця Лермонтова, 57, Кривий Ріг,"
        ),
        Lounge(
            name = "Room",
            id = 2L,
            address = "вулиця Лермонтова, 89, Кривий Ріг,"
        ),
        Lounge(
            name = "Room 7 zarechnii",
            id = 3L,
            address = "вулиця Лермонтова, 177, Кривий Ріг,"
        ),
    )
    LoungeListScreen(list, toLounge)

}

@Composable
private fun LoungeListScreen(list: List<Lounge>, toLounge: ()->(Unit)) {
    HookahLazyColumn(items = list) {
        LoungeContent(it as Lounge, toLounge)
    }
}

@Composable
fun LoungeContent(item: Lounge, toLounge: ()->(Unit)) {
    TextButton(onClick = toLounge) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            HeadlineLarge(
                text = "#${item.id} ${item.name}",
                fontWeight = FontWeight(1000)
            )
            TitleMedium(
                text = item.address,
                fontWeight = FontWeight(500)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoungeListPreview() {
    HookahLoungeTheme {
        LoungeListScreen {}
    }
}