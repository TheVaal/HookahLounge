package com.example.hookahlounge.data.repository.api

import androidx.datastore.core.DataStore
import com.example.hookahlounge.data.datastore.UserPreference
import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.mappers.toModel
import com.example.hookahlounge.data.mappers.toPreference
import com.example.hookahlounge.domain.model.User
import com.example.hookahlounge.domain.repository.api.AuthorizationRepository
import com.example.hookahlounge.domain.util.HookahResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
    private val dataStore: DataStore<UserPreference>
) : AuthorizationRepository {
    override fun authorize(user: User): Flow<HookahResponse<User>> {
        return flow {
            val response = api.getToken(email = user.login, password = user.password)
            if (response.isSuccessful) {

                val result = response.body()!!.toModel()
                dataStore.updateData {
                    it.copy( login = user.login, token = user.authToken)
                }

                emit(HookahResponse.Success(result))
            } else {
                emit(HookahResponse.Error("authorizationError"))
            }
        }
    }

    override fun getDataStoreData(): Flow<User> {
        return dataStore.data.map { it.toModel() }
    }

    override suspend fun updateDataStoreData(user: User) {
        dataStore.updateData {
            user.toPreference()
        }
    }
}