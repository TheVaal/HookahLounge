package com.example.hookahlounge.domain.usecase

import com.example.hookahlounge.data.entity.projection.LoungeWithTables
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toLounge
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.repository.api.LoungeRepository
import com.example.hookahlounge.domain.repository.local.LoungeDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class LoungeUseCase @Inject constructor(
    private val loungeDbRepository: LoungeDbRepository,
    private val loungeRepository: LoungeRepository,
) {
    fun loadLoungeById(loungeId: Long): Flow<HookahResponse<Lounge>> {
        return loungeDbRepository.getLounge(loungeId).map {
                HookahResponse.Success(it.toLounge())
        }.onStart {
            val response: HookahResponse<LoungeWithTables> = loungeRepository.getLounge(loungeId)
            if (response is HookahResponse.Success) {
                loungeDbRepository.upsertLounge(response.data)
            }
        }
    }

    suspend fun postLounge(lounge: Lounge): HookahResponse<Lounge> {

        val res = loungeRepository.postLounge(lounge.toDto())
            .onSuccess {
                loungeDbRepository.upsertLounge(it)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toLounge())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun putLounge(lounge: Lounge): HookahResponse<Lounge> {
        val res = loungeRepository.putLounge(lounge.toDto())
        return if (res is HookahResponse.Success) {
            loungeDbRepository.upsertLounge(res.data)
            HookahResponse.Success(res.data.toLounge())
        } else {
            return res as HookahResponse.Error
        }
    }
}