package com.example.hookahlounge.data.repository.api

import androidx.datastore.core.DataStore
import com.example.hookahlounge.data.datastore.UserPreference
import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.SessionDto
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.mappers.toSessionEntity
import com.example.hookahlounge.domain.repository.api.SessionRepository
import com.example.hookahlounge.domain.util.HookahResponse
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
    private val dataStore: DataStore<UserPreference>,
) : SessionRepository {

    override suspend fun getSessions(): HookahResponse<List<SessionEntity>> {
        return try {
            val sessions = mutableListOf<SessionDto>()
            loadPage(
                addAll = { responseData: List<SessionDto> ->
                    sessions.addAll(responseData)
                }
            )
            HookahResponse.Success(data = sessions.map {
                it.toSessionEntity()
            })

        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }

    }

    private suspend fun loadPage(
        addAll: (List<SessionDto>) -> (Unit),
        currentPage: Int = 1,
    ) {
        val response = api.getSessions(
            currentPage,
            DEFAULT_PAGE_SIZE,
            "Bearer ${dataStore.data.first().token}"
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

    override suspend fun getSession(id: Long): HookahResponse<SessionEntity> {
        return try {
            val response = api.getSession(
                id = id,
                "Bearer ${dataStore.data.first().token}"
            )
            if (response.isSuccessful) {
                val data = response.body()!!.data
                println("HookahSuccess")
                HookahResponse.Success(data.toSessionEntity())

            } else {
                println("HookahError")
                HookahResponse.Error("shit")
            }
        } catch (e: Exception) {
            HookahResponse.Error(e.message.toString())
        }
    }

    override suspend fun putSession(session: SessionDto): HookahResponse<SessionEntity> {
        val response = api.putSession(
            session.id,
            session,
            "Bearer ${dataStore.data.first().token}"
        )
        return if (response.isSuccessful) {
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
            val response = api.postSession(
                session,
                "Bearer ${dataStore.data.first().token}"
            )
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toSessionEntity())
            } else {
                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: Exception) {
            HookahResponse.Error(e.message.toString())
        }
    }
}