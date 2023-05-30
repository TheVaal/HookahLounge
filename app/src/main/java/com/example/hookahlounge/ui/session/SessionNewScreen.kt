package com.example.hookahlounge.ui.session

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.domain.model.User
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahTextField

@Composable
fun SessionNewScreen() {
    val session = Session(
        accessCode = "SR2222",
        id = 1L,
        owner = User(name = "Alexander Vasilenko", id = 1L, phone = "+380684432269"),
        status = false,
        lounge = Lounge(1L, "BadSide", "вулиця Лермонтова, 37, Кривий Ріг,"),
        lockDate = "20230-03-25Z17:00"
    )
    SessionNewScreen(session)

}

@Composable
fun SessionNewScreen(session: Session) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        HookahTextField(value = "", label = "owner Name", onValueChange = { it })
        HookahTextField(value = "", label = "owner phone", onValueChange = { it })
        HookahTextField(value = "", label = "lock date", onValueChange = { it })
        //HookahDateInput()
    }

}