package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "order",
    foreignKeys = [
        ForeignKey(
            entity = SessionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sessionId"]
        ),
        ForeignKey(
            entity = TableEntity::class,
            parentColumns = ["id"],
            childColumns = ["tableId"]
        ),
    ])
data class OrderEntity(
    @PrimaryKey val id: Long,
    val loungeId: Long,
    val tableId: Long,
    val sessionId: Long,
    val sum: Double,
    val closed: Boolean,
    val status: String
)