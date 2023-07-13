package com.example.hookahlounge.domain.repository.datastore

import com.example.hookahlounge.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserPrefsDataStoreRepository {
    suspend fun getData(): Flow<User>

    suspend fun updateData(user: User)
}