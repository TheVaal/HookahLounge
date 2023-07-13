package com.example.hookahlounge.domain.usecase

import com.example.hookahlounge.data.entity.projection.InOrderWithFields
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toModel
import com.example.hookahlounge.domain.model.InOrder
import com.example.hookahlounge.domain.repository.api.InOrderRepository
import com.example.hookahlounge.domain.repository.local.InOrderDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class InOrderUseCase @Inject constructor(
    private val inOrderRepository: InOrderRepository,
    private val inOrderDbRepository: InOrderDbRepository,
) {
    suspend fun loadInOrder(orderId: Long): Flow<HookahResponse<List<InOrder>>> {
        return inOrderDbRepository.getInOrder(orderId).map { list ->
            HookahResponse.Success(
                list.map { inOrder ->
                    inOrder.toModel()
                }
            )
        }.onStart {
            val response: HookahResponse<List<InOrderWithFields>> =
                inOrderRepository.getInOrder(orderId)
            if (response is HookahResponse.Success) {
                inOrderDbRepository.upsertAll(response.data.map{
                    it.inOrder
                })
            }
        }
    }

    suspend fun putInOrder(inOrder: InOrder): HookahResponse<InOrder> {
        val res = inOrderRepository.putInOrder(inOrder.toDto())
            .onSuccess {
                inOrderDbRepository.upsertInOrder(it.inOrder)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun postInOrder(inOrder: InOrder): HookahResponse<InOrder> {
        val res = inOrderRepository.postInOrder(inOrder.toDto())
            .onSuccess {
                inOrderDbRepository.upsertInOrder(it.inOrder)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }
}