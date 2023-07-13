package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("inOrder")
data class InOrderEntity(
    @PrimaryKey val id: Long,
    val orderId: Long,
    val guest: Int,
    val menuId: Long,
    val quantity: Double,
)