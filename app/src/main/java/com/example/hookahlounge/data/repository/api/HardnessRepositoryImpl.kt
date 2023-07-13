package com.example.hookahlounge.data.repository.api

import androidx.datastore.core.DataStore
import com.example.hookahlounge.data.datastore.UserPreference
import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.HardnessDto
import com.example.hookahlounge.data.entity.core.HardnessEntity
import com.example.hookahlounge.data.mappers.toEntity
import com.example.hookahlounge.domain.repository.api.HardnessRepository
import com.example.hookahlounge.domain.util.HookahResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import retrofit2.HttpException
import javax.inject.Inject

class HardnessRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
    private val dataStore: DataStore<UserPreference>,
): HardnessRepository {
    override suspend fun getHardness(): HookahResponse<List<HardnessEntity>> {
        return try {
            val hardness = mutableListOf<HardnessDto>()
            loadPage(
                addAll = { responseData: List<HardnessDto> ->
                    hardness.addAll(responseData)
                }
            )
            HookahResponse.Success(data = hardness.map {
                it.toEntity()
            })

        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    private suspend fun loadPage(
        addAll: (List<HardnessDto>) -> (Unit),
        currentPage: Int = 1
    ) {
        val response = api.getHardness(
            page = currentPage,
            auth = "Bearer ${dataStore.data.first().token}"
        )
        if (response.isSuccessful) {
            val responseMetadata = response.body()!!.meta
            addAll(response.body()!!.data)
            if (responseMetadata.currentPage!! < responseMetadata.lastPage!!) {
                loadPage(
                    addAll = addAll,
                    currentPage = currentPage.plus(1)
                )
            }
        }
    }

    override suspend fun getHardnessById(id: Long): HookahResponse<HardnessEntity> {
        return try {
            val response = api.getHardnessById(
                id = id,
                auth = "Bearer ${dataStore.data.first().token}"
            )
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntity())
            } else {
                HookahResponse.Error("Unable to reach api host")
            }
        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    override suspend fun putHardness(hardness: HardnessDto): HookahResponse<HardnessEntity> {
        val response = api.putHardness(
            id = hardness.id,
            hardness = hardness,
            auth = "Bearer ${dataStore.data.last().token}"
        )
        return if (response.isSuccessful){
            HookahResponse.Success(hardness.toEntity())
        } else {
            HookahResponse.Error("Put data error")
        }
    }

    override suspend fun postHardness(hardness: HardnessDto): HookahResponse<HardnessEntity> {
        return try {
            val response = api.postHardness(
                hardness = hardness,
                auth = "Bearer ${dataStore.data.first().token}"
            )
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntity())
            } else {
                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: HttpException){
            HookahResponse.Error(e.message())
        }
    }
}