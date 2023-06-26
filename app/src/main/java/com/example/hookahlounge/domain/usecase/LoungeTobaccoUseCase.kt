package com.example.hookahlounge.domain.usecase

import com.example.hookahlounge.data.entity.projection.LoungeTobaccoWithFields
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toModel
import com.example.hookahlounge.domain.model.LoungeTobacco
import com.example.hookahlounge.domain.repository.api.LoungeTobaccoRepository
import com.example.hookahlounge.domain.repository.local.LoungeTobaccoDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class LoungeTobaccoUseCase@Inject constructor(
    private val loungeTobaccoRepository: LoungeTobaccoRepository,
    private val loungeTobaccoDbRepository: LoungeTobaccoDbRepository,
) {

    suspend fun loadTobaccoById(tobaccoId: Long): Flow<HookahResponse<LoungeTobacco>> {
        return loungeTobaccoDbRepository.getTobaccoById(tobaccoId).map {
            HookahResponse.Success(it.toModel())
        }.onStart {
            val response: HookahResponse<LoungeTobaccoWithFields> = loungeTobaccoRepository.getLoungeTobaccoById(tobaccoId)
            if (response is HookahResponse.Success) {
                loungeTobaccoDbRepository.upsertLoungeTobacco(response.data.loungeTobacco)
            }
        }
    }

    suspend fun loadTobaccos(loungeId: Long): Flow<HookahResponse<List<LoungeTobacco>>> {
        return loungeTobaccoDbRepository.getTobacco(loungeId).map { list ->
            HookahResponse.Success(
                list.map { loungeTobacco ->
                    loungeTobacco.toModel()
                }
            )
        }.onStart {
            val response: HookahResponse<List<LoungeTobaccoWithFields>> =
                loungeTobaccoRepository.getLoungeTobacco(loungeId)
            if (response is HookahResponse.Success) {
                loungeTobaccoDbRepository.upsertAll(
                    response.data.map {
                        it.loungeTobacco
                    }
                )
            }
        }
    }

    suspend fun postTobacco(loungeTobacco: LoungeTobacco): HookahResponse<LoungeTobacco> {

        val res = loungeTobaccoRepository.postLoungeTobacco(loungeTobacco.toDto())
            .onSuccess {
                loungeTobaccoDbRepository.upsertLoungeTobacco(it.loungeTobacco)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun putLoungeTobacco(loungeTobacco: LoungeTobacco): HookahResponse<LoungeTobacco> {
        val res = loungeTobaccoRepository.putLoungeTobacco(loungeTobacco.toDto())
        return if (res is HookahResponse.Success) {
            loungeTobaccoDbRepository.upsertLoungeTobacco(res.data.loungeTobacco)
            HookahResponse.Success(res.data.toModel())
        } else {
            return res as HookahResponse.Error
        }
    }
}