package com.example.hookahlounge.presentation.table.viewmodel

sealed class TableEvent {
    data class EnteredName(val value: String): TableEvent()
    data class EnteredSize(val value: String): TableEvent()
}
