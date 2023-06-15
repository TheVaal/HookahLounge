package com.example.hookahlounge.data.repository.api

import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.LoungeDto
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.projection.LoungeWithTables
import com.example.hookahlounge.data.mappers.toLoungeEntity
import com.example.hookahlounge.data.mappers.toLoungeWithTables
import com.example.hookahlounge.domain.repository.api.LoungeRepository
import com.example.hookahlounge.domain.util.HookahResponse
import retrofit2.HttpException
import javax.inject.Inject

class LoungeRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
) : LoungeRepository {
    override suspend fun getLounges(): HookahResponse<List<LoungeEntity>> {

        try {
            val currentPage = 1
            loadApiPage(currentPage)

        } catch (e :Exception){
            println(e.message)
        }
        return HookahResponse.Success(data = listOf())
    }

    private suspend fun loadApiPage(currentPage: Int){
        val response = api.getLounges(currentPage, 50)
        if (response.isSuccessful){
            val metadata = response.body()!!.meta
            val data = response.body()!!.data
            data.map {
                it.toLoungeEntity()
            }
            if (metadata.currentPage != metadata.lastPage){
                loadApiPage(currentPage.plus(1))
            }
        }
    }

    override suspend fun getLounge(id: Long): HookahResponse<LoungeWithTables> {
        val response = api.getLounge(id = id)
        return if (response.isSuccessful){
            val data = response.body()!!.data
            println("HookahSuccess")
            HookahResponse.Success(data.toLoungeWithTables())

        }else{
            println("HookahError")
            HookahResponse.Error("shit")
        }

    }

    override suspend fun postLounge(lounge: LoungeDto): HookahResponse<LoungeEntity> {
        return try {
            val response = api.postLounge(lounge)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toLoungeEntity())
            } else {
                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: HttpException){
            HookahResponse.Error(e.message())
        }
    }

    override suspend fun putLounge(lounge: LoungeDto): HookahResponse<LoungeEntity> {
        val response = api.putLounge(lounge.id, lounge)
        return if (response.isSuccessful){
            HookahResponse.Success(lounge.toLoungeEntity())
        } else {
            response.errorBody()
            response.message()
            response.body()
            HookahResponse.Error("Put data error")
        }
    }
}