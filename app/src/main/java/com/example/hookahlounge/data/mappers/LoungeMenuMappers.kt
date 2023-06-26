package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.LoungeMenuDto
import com.example.hookahlounge.data.dto.datasource.MenuDto
import com.example.hookahlounge.data.entity.core.LoungeMenuEntity
import com.example.hookahlounge.data.entity.core.MenuEntity
import com.example.hookahlounge.data.entity.projection.LoungeMenuWithFields
import com.example.hookahlounge.domain.model.LoungeMenu
import com.example.hookahlounge.domain.model.Menu

fun LoungeMenuDto.toEntity(): LoungeMenuEntity {
    return LoungeMenuEntity(
        id = id,
        loungeId = loungeId,
        menuId = menuId,
        price = price
    )
}

fun LoungeMenuDto.toEntityWithFields(): LoungeMenuWithFields {
    return LoungeMenuWithFields(
        loungeMenu = toEntity(),
        menu = menu.toEntity()
    )
}

fun LoungeMenuWithFields.toModel(): LoungeMenu{
    return LoungeMenu(
        id = loungeMenu.id,
        loungeId = loungeMenu.loungeId,
        menuId = loungeMenu.menuId,
        menu = menu.toModel(),
        price = loungeMenu.price.toString()
    )
}

fun LoungeMenu.toDto(): LoungeMenuDto {
    return LoungeMenuDto(
        id = id,
        loungeId = loungeId,
        menuId = menuId,
        price = price.toDouble()
    )
}

fun MenuDto.toEntity(): MenuEntity {
    return MenuEntity(
        id = id,
        name = name
    )
}

fun MenuEntity.toModel(): Menu {
    return Menu(
        id = id,
        name = name
    )
}

fun Menu.toDto(): MenuDto {
    return MenuDto(
        id = id,
        name = name
    )
}
