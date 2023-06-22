package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.LoungeMenuEntity
import com.example.hookahlounge.data.entity.core.MenuEntity

data class LoungeMenuWithFields (
    @Embedded val loungeMenu: LoungeMenuEntity,
    @Relation(parentColumn = "menuId", entityColumn = "id")
    val menu: MenuEntity,
)