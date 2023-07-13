package com.example.hookahlounge.data.repository.api

import androidx.datastore.core.DataStore
import com.example.hookahlounge.data.datastore.UserPreference
import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.InOrderDto
import com.example.hookahlounge.data.entity.projection.InOrderWithFields
import com.example.hookahlounge.data.mappers.toEntityWithFields
import com.example.hookahlounge.domain.repository.api.InOrderRepository
import com.example.hookahlounge.domain.util.HookahResponse
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import javax.inject.Inject

class InOrderRepositoryImpl@Inject constructor(
    private val api: HookahLoungeApi,
    private val dataStore: DataStore<UserPreference>
): InOrderRepository {

    override suspend fun getInOrder(orderId: Long): HookahResponse<List<InOrderWithFields>> {
        return try {
            val inOrder = mutableListOf<InOrderDto>()
            loadPage(
                addAll = { responseData: List<InOrderDto> ->
                    inOrder.addAll(responseData)
                },
                orderId = orderId
            )
            HookahResponse.Success(data = inOrder.map {
                it.toEntityWithFields()
            })

        } catch (e: Exception) {
            HookahResponse.Error(e.message.toString())
        }
    }

    private suspend fun loadPage(
        addAll: (List<InOrderDto>) -> (Unit),
        orderId: Long,
        currentPage: Int = 1
    ) {
        val response = api.getInOrder(
            currentPage,
            orderId,
            "Bearer ${dataStore.data.first().token}"
        )
        if (response.isSuccessful) {
            val responseMetadata = response.body()!!.meta
            addAll(response.body()!!.data)
            if (responseMetadata.currentPage!! < responseMetadata.lastPage!!) {
                loadPage(
                    addAll = addAll,
                    currentPage = currentPage.plus(1),
                    orderId = orderId
                )
            }
        }
    }

    override suspend fun getInOrderById(id: Long): HookahResponse<InOrderWithFields> {
        return try {
            val response = api.getInOrder(
                id,
                "Bearer ${dataStore.data.first().token}"
            )
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntityWithFields())
            } else {
                HookahResponse.Error("Unable to reach api host")
            }
        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    override suspend fun putInOrder(inOrder: InOrderDto): HookahResponse<InOrderWithFields> {
        val response = api.putInOrder(
            inOrder.id,
            inOrder,
            "Bearer ${dataStore.data.first().token}"
        )
        return if (response.isSuccessful){
            HookahResponse.Success(inOrder.toEntityWithFields())
        } else {
            HookahResponse.Error("Put data error")
        }
    }

    override suspend fun postInOrder(inOrder: InOrderDto): HookahResponse<InOrderWithFields> {
        return try {
            val response = api.postInOrder(
                inOrder,
                "Bearer ${dataStore.data.first().token}"
            )
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntityWithFields())
            } else {
                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: HttpException){
            HookahResponse.Error(e.message())
        }
    }
}