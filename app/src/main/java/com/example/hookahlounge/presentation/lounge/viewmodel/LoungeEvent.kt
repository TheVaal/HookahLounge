package com.example.hookahlounge.presentation.lounge.viewmodel

sealed class LoungeEvent {
    data class EnteredName(val value: String) : LoungeEvent()
    data class EnteredCountry(val value: String) : LoungeEvent()
    data class EnteredCity(val value: String) : LoungeEvent()
    data class EnteredPostalCode(val value: String) : LoungeEvent()
    data class EnteredAddress(val value: String) : LoungeEvent()
    data class EnteredState(val value: String) : LoungeEvent()
}
