package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.InOrderDto
import com.example.hookahlounge.data.entity.core.InOrderEntity
import com.example.hookahlounge.data.entity.projection.InOrderWithFields
import com.example.hookahlounge.domain.model.InOrder

fun InOrderDto.toEntityWithFields(): InOrderWithFields{
    return InOrderWithFields(
        inOrder = toEntity(),
        menuItem = loungeMenu.toEntityWithFields()
    )
}


fun InOrderDto.toEntity(): InOrderEntity {
    return InOrderEntity(
        id = id,
        menuId = loungeMenuId,
        guest = guest,
        quantity = quantity,
        orderId = orderId
    )
}

fun InOrderWithFields.toModel(): InOrder {
    return InOrder(
        id = inOrder.id,
        orderId = inOrder.orderId,
        guest = inOrder.guest,
        menuId = inOrder.menuId,
        menu = menuItem.toModel(),
        quantity = inOrder.quantity.toString()
    )
}

fun InOrder.toDto(): InOrderDto {
    return InOrderDto(
        id = id,
        orderId = orderId,
        guest = guest,
        loungeMenuId = menuId,
        quantity = quantity.toDouble()
    )
}