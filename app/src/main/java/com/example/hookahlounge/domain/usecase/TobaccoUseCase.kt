package com.example.hookahlounge.domain.usecase

import com.example.hookahlounge.data.entity.projection.TobaccoWithFields
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toModel
import com.example.hookahlounge.domain.model.Tobacco
import com.example.hookahlounge.domain.repository.api.TobaccoRepository
import com.example.hookahlounge.domain.repository.local.TobaccoDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class TobaccoUseCase@Inject constructor(
    private val tobaccoRepository: TobaccoRepository,
    private val tobaccoDbRepository: TobaccoDbRepository,
)  {
    suspend fun loadTobaccoById(tobaccoId: Long): Flow<HookahResponse<Tobacco>> {


        return tobaccoDbRepository.getTobaccoById(tobaccoId).map {
            HookahResponse.Success(it.toModel())
        }.onStart {
            val response: HookahResponse<TobaccoWithFields> = tobaccoRepository.getTobaccoById(tobaccoId)
            if (response is HookahResponse.Success) {
                tobaccoDbRepository.upsertTobacco(response.data.tobacco)
            }
        }
    }

    suspend fun loadTobaccos(): Flow<HookahResponse<List<Tobacco>>> {
        return tobaccoDbRepository.getTobacco().map { list ->
            HookahResponse.Success(
                list.map { tobacco ->
                    tobacco.toModel()
                }
            )
        }.onStart {
            val response: HookahResponse<List<TobaccoWithFields>> =
                tobaccoRepository.getTobacco()
            if (response is HookahResponse.Success) {
                tobaccoDbRepository.upsertAll(
                    response.data.map {
                        it.tobacco
                    }
                )
            }
        }
    }

    suspend fun postTobacco(tobacco: Tobacco): HookahResponse<Tobacco> {
        val res = tobaccoRepository.postTobacco(tobacco.toDto())
            .onSuccess {
                tobaccoDbRepository.upsertTobacco(it.tobacco)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun putTobacco(tobacco: Tobacco): HookahResponse<Tobacco> {
        val res = tobaccoRepository.putTobacco(tobacco.toDto())
        return if (res is HookahResponse.Success) {
            tobaccoDbRepository.upsertTobacco(res.data.tobacco)
            HookahResponse.Success(res.data.toModel())
        } else {
            return res as HookahResponse.Error
        }
    }
}