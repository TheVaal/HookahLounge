package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("hardness")
data class HardnessEntity(
    @PrimaryKey val id: Long,
    val name: String,
)