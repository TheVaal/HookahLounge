package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.core.SessionEntity

data class SessionWithFields(
    @Embedded val session: SessionEntity,
    @Relation(parentColumn = "loungeId", entityColumn = "id")
    val lounge: LoungeEntity,
)
