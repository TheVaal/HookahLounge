package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.OrderEntity
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.entity.core.TableEntity
import com.example.hookahlounge.domain.model.InOrder

data class OrderWithFields(
    @Embedded val order: OrderEntity,
    @Relation(parentColumn = "tableId", entityColumn = "id")
    val table: TableEntity,
    @Relation(parentColumn = "sessionId", entityColumn = "id")
    val session: SessionEntity,
    @Relation(parentColumn = "id", entityColumn = "orderId")
    val inOrder: List<InOrder>,
    @Relation(parentColumn = "id", entityColumn = "orderId")
    val mixes: List<MixWithFields>,
)
