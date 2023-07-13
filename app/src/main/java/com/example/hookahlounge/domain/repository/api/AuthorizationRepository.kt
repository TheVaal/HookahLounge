package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.domain.model.User
import com.example.hookahlounge.domain.util.HookahResponse
import kotlinx.coroutines.flow.Flow

interface AuthorizationRepository {
    fun authorize(user: User): Flow<HookahResponse<User>>

    fun getDataStoreData(): Flow<User>

    suspend fun updateDataStoreData(user: User)
}