package com.example.hookahlounge.domain.usecase

import com.example.hookahlounge.data.entity.projection.MixWithFields
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toModel
import com.example.hookahlounge.domain.model.Hookah
import com.example.hookahlounge.domain.model.Mix
import com.example.hookahlounge.domain.repository.api.HookahMixRepository
import com.example.hookahlounge.domain.repository.local.HookahMixDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HookahMixUseCase@Inject constructor(
    private val hookahMixRepository: HookahMixRepository,
    private val hookahMixDbRepository: HookahMixDbRepository,
){
    suspend fun loadHookahMix(orderId: Long): Flow<HookahResponse<List<Mix>>> {
        return hookahMixDbRepository.getMix(orderId).map { list ->
            HookahResponse.Success(
                list.map { mix ->
                    mix.toModel()
                }
            )
        }.onStart {
            val response: HookahResponse<List<MixWithFields>> =
                hookahMixRepository.getMixByOrder(orderId)
            if (response is HookahResponse.Success) {
                hookahMixDbRepository.upsertMixes(response.data)
            }
        }
    }

    suspend fun putHookahMix(mix: Mix): HookahResponse<Mix> {
        val res = hookahMixRepository.putMix(mix.toDto())
            .onSuccess { mixEntity ->
                hookahMixDbRepository.upsertMix(mixEntity.mix)
                // postPutHookah
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun postMix(mix: Mix): HookahResponse<Mix> {
        val res = hookahMixRepository.postMix(mix.toDto())
            .onSuccess {mixEntity->
                hookahMixDbRepository.upsertMix(mixEntity.mix)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun postHookah(hookah: Hookah): HookahResponse<Hookah> {
        val res = hookahMixRepository.postHookah(hookah.toDto())
            .onSuccess {hookahEntity->
                hookahMixDbRepository.upsertHookah(hookahEntity.hookah)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun putHookah(hookah: Hookah): HookahResponse<Hookah> {
        val res = hookahMixRepository.putHookah(hookah.toDto())
            .onSuccess { hookahEntity ->
                hookahMixDbRepository.upsertHookah(hookahEntity.hookah)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }
}