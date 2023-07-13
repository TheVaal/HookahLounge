package com.example.hookahlounge.data.repository.api

import androidx.datastore.core.DataStore
import com.example.hookahlounge.data.datastore.UserPreference
import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.HookahDto
import com.example.hookahlounge.data.dto.datasource.MixDto
import com.example.hookahlounge.data.entity.projection.HookahEntityWithFields
import com.example.hookahlounge.data.entity.projection.MixWithFields
import com.example.hookahlounge.data.mappers.toEntityWithFields
import com.example.hookahlounge.domain.repository.api.HookahMixRepository
import com.example.hookahlounge.domain.util.HookahResponse
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import javax.inject.Inject

class HookahMixRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
    private val dataStore: DataStore<UserPreference>,
) : HookahMixRepository {

    override suspend fun getMixByOrder(orderId: Long): HookahResponse<List<MixWithFields>> {
        return try {
            val mixes = mutableListOf<MixDto>()
            loadPage(
                addAll = { responseData: List<MixDto> ->
                    mixes.addAll(responseData)
                },
                orderId = orderId
            )
            HookahResponse.Success(data = mixes.map {
                it.toEntityWithFields()
            })

        } catch (e: Exception) {
            HookahResponse.Error(e.message.toString())
        }
    }

    private suspend fun loadPage(
        addAll: (List<MixDto>) -> (Unit),
        orderId: Long,
        currentPage: Int = 1
    ) {
        val response = api.getMixByOrder(
            page = currentPage,
            orderId = orderId,
            auth = "Bearer ${dataStore.data.first().token}")
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
    override suspend fun getMixById(id: Long): HookahResponse<MixWithFields> {
        return try {
            val response = api.getMixById(
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

    override suspend fun putMix(mix: MixDto): HookahResponse<MixWithFields> {
        val response = api.putMix(
            mix.id,
            mix,
            "Bearer ${dataStore.data.first().token}"
        )
        return if (response.isSuccessful){
            HookahResponse.Success(mix.toEntityWithFields())
        } else {
            HookahResponse.Error("Put data error")
        }
    }

    override suspend fun postMix(mix: MixDto): HookahResponse<MixWithFields> {
        return try {
            val response = api.postMix(
                mix,
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

    override suspend fun putHookah(hookah: HookahDto): HookahResponse<HookahEntityWithFields> {
        val response = api.putHookah(
            hookah.id,
            hookah,
            "Bearer ${dataStore.data.first().token}"
        )
        return if (response.isSuccessful){
            HookahResponse.Success(hookah.toEntityWithFields())
        } else {
            HookahResponse.Error("Put data error")
        }
    }

    override suspend fun postHookah(hookah: HookahDto): HookahResponse<HookahEntityWithFields> {
        return try {
            val response = api.postHookah(
                hookah,
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