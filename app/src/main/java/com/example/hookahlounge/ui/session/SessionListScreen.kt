package com.example.hookahlounge.ui.session

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
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
import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.domain.model.User
import com.example.hookahlounge.ui.theme.HookahLoungeTheme
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HeadlineLarge
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahLazyColumn
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahScaffold
import com.example.hookahlounge.ui.theme.hookah_ui_elements.TitleLarge

@Composable
fun SessionListScreen() {
    val list: List<Session> = listOf(
        Session(
            accessCode = "SR2222",
            id = 1L,
            owner = User(name = "Alexander Vasilenko", id = 1L, phone = "+380684432269"),
            status = false,
            lounge = Lounge(1L, "BadSide", "вулиця Лермонтова, 37, Кривий Ріг,"),
            lockDate = "20230-03-25Z17:00"
        ),
        Session(
            accessCode = "RT6943",
            id = 2L,
            owner = User(name = "Vasil Petrov", id = 2L, phone = "+380684532777"),
            status = true,
            lounge = Lounge(1L, "BadSide", "вулиця Лермонтова, 37, Кривий Ріг,"),
            lockDate = "20230-03-25Z17:00"
        ),
        Session(
            accessCode = "RT6922",
            id = 2L,
            owner = User(name = "Valerii Rozier III", id = 2L, phone = "+380686632269"),
            status = false,
            lounge = Lounge(1L, "BadSide", "вулиця Лермонтова, 37, Кривий Ріг,"),
            lockDate = "20230-03-25Z17:00"
        ),
        Session(
            accessCode = "RT6923",
            id = 3L,
            owner = User(name = "Richard", id = 1L, phone = "+380688877269"),
            status = true,
            lounge = Lounge(1L, "BadSide", "вулиця Лермонтова, 37, Кривий Ріг,"),
            lockDate = "20230-03-25Z17:00"
        ),
        Session(
            accessCode = "RT6923",
            id = 3L,
            owner = User(name = "kareem abdul-jabbar", id = 1L, phone = "+38068882231"),
            status = true,
            lounge = Lounge(1L, "BadSide", "вулиця Лермонтова, 37, Кривий Ріг,"),
            lockDate = "20230-03-25Z17:00"
        ),
    )
    HookahScaffold {
        SessionListScreen(list)
    }
}

@Composable
private fun SessionListScreen(list: List<Session>) {
    HookahLazyColumn(items = list) {
        SessionContent(it as Session)
    }
}

@Composable
private fun SessionContent(item: Session) {
    TextButton(onClick = { /*TODO*/ }) {
        Row(
            modifier = Modifier
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                contentDescription = null,
                painter = getSessionStatusIcon(item.status),
                modifier = Modifier.fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                HeadlineLarge(
                    text = item.owner.name,
                    fontWeight = FontWeight(1000)
                )
                TitleLarge(
                    text = item.owner.phone,
                    fontWeight = FontWeight(500)
                )
                TitleLarge(
                    text = item.lockDate,
                    fontWeight = FontWeight(500)
                )
            }
        }
    }
}

@Composable
private fun getSessionStatusIcon(status:Boolean): Painter {
    return if (status) {
        painterResource(id = R.drawable.ic_lock_24)
    } else {
        painterResource(id = R.drawable.ic_lock_open_24)
    }
}

@Preview(showBackground = true)
@Composable
fun LoungeListPreview() {
    HookahLoungeTheme {
        SessionListScreen()
    }
}