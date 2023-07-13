package com.example.hookahlounge.data.repository.datastore

import androidx.datastore.core.DataStore
import com.example.hookahlounge.data.datastore.UserPreference
import com.example.hookahlounge.data.mappers.toModel
import com.example.hookahlounge.data.mappers.toPreference
import com.example.hookahlounge.domain.model.User
import com.example.hookahlounge.domain.repository.datastore.UserPrefsDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPrefsDataStoreRepositoryImpl @Inject constructor(
    private val userDataStore: DataStore<UserPreference>,
): UserPrefsDataStoreRepository {
    override suspend fun getData(): Flow<User> {
        return userDataStore.data.map { it.toModel() }
    }

    override suspend fun updateData(user: User) {
        userDataStore.updateData {
            user.toPreference()
        }
    }

}