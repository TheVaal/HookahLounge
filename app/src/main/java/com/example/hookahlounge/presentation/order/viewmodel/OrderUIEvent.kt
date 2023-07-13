package com.example.hookahlounge.presentation.order.viewmodel

import com.example.hookahlounge.domain.model.InOrder
import com.example.hookahlounge.domain.model.LoungeMenu
import com.example.hookahlounge.domain.model.LoungeTobacco
import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.domain.model.Table

enum class OrderStatus(val value: String) {
    BOOKED("B"),
    CANCELED("C"),
    OPEN("O"),
    PAID("P")
}

sealed class OrderUIEvent {
    data class SelectedSession(val value: Session) : OrderUIEvent()
    data class SelectedTable(val value: Table) : OrderUIEvent()
    data class ChangeStatus(val value: String) : OrderUIEvent()
    data class ChangeGuests(val value: String) : OrderUIEvent()
    data class ChangeQuantity(val inOrder: InOrder, val newValue: String) : OrderUIEvent()
    data class AddInOrder(val menu: LoungeMenu, val guest: Int, val quantity: Double) : OrderUIEvent()
    data class AddHookah(val tobacco: LoungeTobacco, val mixId: Long, val weight: String) : OrderUIEvent()
    object PostOrder : OrderUIEvent()
    object AddMix : OrderUIEvent()
}