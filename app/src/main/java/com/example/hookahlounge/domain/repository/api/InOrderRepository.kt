package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.InOrderDto
import com.example.hookahlounge.data.entity.projection.InOrderWithFields
import com.example.hookahlounge.domain.util.HookahResponse

interface InOrderRepository {

    suspend fun getInOrder(orderId: Long) : HookahResponse<List<InOrderWithFields>>

    suspend fun getInOrderById(id: Long): HookahResponse<InOrderWithFields>

    suspend fun putInOrder(inOrder: InOrderDto): HookahResponse<InOrderWithFields>

    suspend fun postInOrder(inOrder: InOrderDto): HookahResponse<InOrderWithFields>

}