package com.example.hookahlounge.presentation.menu.viewmodel.single

sealed class LoungeMenuEvent {
    data class EnteredName(val value: String) : LoungeMenuEvent()
    data class EnteredPrice(val value: String) : LoungeMenuEvent()

}