package com.example.hookahlounge.domain.usecase

import com.example.hookahlounge.data.entity.core.HardnessEntity
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toModel
import com.example.hookahlounge.domain.model.Hardness
import com.example.hookahlounge.domain.repository.api.HardnessRepository
import com.example.hookahlounge.domain.repository.local.HardnessDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HardnessUseCase@Inject constructor(
    private val hardnessRepository: HardnessRepository,
    private val hardnessDbRepository: HardnessDbRepository,
)  {
    suspend fun loadHardnessById(HardnessId: Long): Flow<HookahResponse<Hardness>> {
        return hardnessDbRepository.getHardnessById(HardnessId).map {
            HookahResponse.Success(it.toModel())
        }.onStart {
            val response: HookahResponse<HardnessEntity> =
                hardnessRepository.getHardnessById(HardnessId)
            if (response is HookahResponse.Success) {
                hardnessDbRepository.upsertHardness(response.data)
            }
        }
    }

    suspend fun loadHardness(): Flow<HookahResponse<List<Hardness>>> {
        return hardnessDbRepository.getHardness().map { list ->
            HookahResponse.Success(
                list.map { hardness ->
                    hardness.toModel()
                }
            )
        }.onStart {
            val response: HookahResponse<List<HardnessEntity>> =
                hardnessRepository.getHardness()
            if (response is HookahResponse.Success) {
                hardnessDbRepository.upsertAll(response.data)
            }
        }
    }

    suspend fun postHardness(hardness: Hardness): HookahResponse<Hardness> {

        val res = hardnessRepository.postHardness(hardness.toDto())
            .onSuccess {
                hardnessDbRepository.upsertHardness(it)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun putHardness(hardness: Hardness): HookahResponse<Hardness> {
        val res = hardnessRepository.putHardness(hardness.toDto())
        return if (res is HookahResponse.Success) {
            hardnessDbRepository.upsertHardness(res.data)
            HookahResponse.Success(res.data.toModel())
        } else {
            return res as HookahResponse.Error
        }
    }
}