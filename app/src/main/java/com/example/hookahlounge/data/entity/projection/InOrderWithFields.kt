package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.InOrderEntity
import com.example.hookahlounge.data.entity.core.LoungeMenuEntity

data class InOrderWithFields(
    @Embedded val inOrder: InOrderEntity,
    @Relation(parentColumn = "menuId", entityColumn = "id", entity = LoungeMenuEntity::class)
    val menuItem: LoungeMenuWithFields,
)