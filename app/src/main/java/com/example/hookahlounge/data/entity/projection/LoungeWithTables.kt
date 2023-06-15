package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.core.TableEntity

data class LoungeWithTables(
    @Embedded val lounge: LoungeEntity,
    @Relation(parentColumn = "id", entityColumn = "loungeId")
    val tables: List<TableEntity> = listOf(),
)
