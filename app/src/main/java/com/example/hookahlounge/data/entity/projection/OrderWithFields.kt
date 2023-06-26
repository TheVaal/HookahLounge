package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.InOrderEntity
import com.example.hookahlounge.data.entity.core.MixEntity
import com.example.hookahlounge.data.entity.core.OrderEntity
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.entity.core.TableEntity

data class OrderWithFields(
    @Embedded val order: OrderEntity,
    @Relation(parentColumn = "tableId", entityColumn = "id")
    val table: TableEntity,
    @Relation(parentColumn = "sessionId", entityColumn = "id", entity = SessionEntity::class)
    val session: SessionWithFields,
    @Relation(parentColumn = "id", entityColumn = "orderId")
    val inOrder: List<InOrderEntity>,
    @Relation(parentColumn = "id", entityColumn = "orderId", entity = MixEntity::class)
    val mixes: List<MixWithFields>,
)
