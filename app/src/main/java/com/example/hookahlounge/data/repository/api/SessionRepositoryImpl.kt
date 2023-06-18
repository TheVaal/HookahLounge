package com.example.hookahlounge.data.repository.api

import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.SessionDto
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.mappers.toSessionEntity
import com.example.hookahlounge.domain.repository.api.SessionRepository
import com.example.hookahlounge.domain.util.HookahResponse
import retrofit2.HttpException
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    val api: HookahLoungeApi
) : SessionRepository {


    override suspend fun getSession(id: Long): HookahResponse<SessionEntity> {
        val response = api.getSession(id = id)
        return if (response.isSuccessful){
            val data = response.body()!!.data
            println("HookahSuccess")
            HookahResponse.Success(data.toSessionEntity())

        }else{
            println("HookahError")
            HookahResponse.Error("shit")
        }
    }

    override suspend fun putSession(session: SessionDto): HookahResponse<SessionEntity> {
        val response = api.putSession(session.id, session)
        return if (response.isSuccessful){
            HookahResponse.Success(session.toSessionEntity())
        } else {
            response.errorBody()
            response.message()
            response.body()
            HookahResponse.Error("Put data error")
        }
    }

    override suspend fun postSession(session: SessionDto): HookahResponse<SessionEntity> {
        return try {
            val response = api.postSession(session)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toSessionEntity())
            } else {
                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: HttpException){
            HookahResponse.Error(e.message())
        }
    }
}