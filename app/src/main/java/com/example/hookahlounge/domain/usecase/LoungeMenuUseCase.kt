package com.example.hookahlounge.domain.usecase

import com.example.hookahlounge.data.entity.projection.LoungeMenuWithFields
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toModel
import com.example.hookahlounge.domain.model.LoungeMenu
import com.example.hookahlounge.domain.repository.api.LoungeMenuRepository
import com.example.hookahlounge.domain.repository.local.LoungeMenuDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class LoungeMenuUseCase @Inject constructor(
    private val loungeMenuDbRepository: LoungeMenuDbRepository,
    private val loungeMenuRepository: LoungeMenuRepository,
) {
    suspend fun loadMenuById(menuId: Long): Flow<HookahResponse<LoungeMenu>> {
        return loungeMenuDbRepository.getLoungeMenuById(menuId).map {
            HookahResponse.Success(it.toModel())
        }.onStart {
            val response: HookahResponse<LoungeMenuWithFields> = loungeMenuRepository.loadLoungeMenuById(menuId)
            if (response is HookahResponse.Success) {
                loungeMenuDbRepository.upsertLoungeMenu(response.data.loungeMenu)
            }
        }
    }

    suspend fun loadMenus(loungeId: Long): Flow<HookahResponse<List<LoungeMenu>>> {
        return loungeMenuDbRepository.getLoungeMenu(loungeId).map { list ->
            HookahResponse.Success(
                list.map { lounge ->
                    lounge.toModel()
                }
            )
        }.onStart {
            val response: HookahResponse<List<LoungeMenuWithFields>> =
                loungeMenuRepository.loadLoungeMenuByLoungeId(loungeId)
            if (response is HookahResponse.Success) {
                loungeMenuDbRepository.upsertAll(
                    response.data.map {
                        it.loungeMenu
                    }
                )
            }
        }
    }

    suspend fun postMenu(loungeMenu: LoungeMenu): HookahResponse<LoungeMenu> {

        val res = loungeMenuRepository.postLoungeMenu(loungeMenu.toDto())
            .onSuccess {
                loungeMenuDbRepository.upsertLoungeMenu(it.loungeMenu)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun putLoungeMenu(loungeMenu: LoungeMenu): HookahResponse<LoungeMenu> {
        val res = loungeMenuRepository.putLoungeMenu(loungeMenu.toDto())
        return if (res is HookahResponse.Success) {
            loungeMenuDbRepository.upsertLoungeMenu(res.data.loungeMenu)
            HookahResponse.Success(res.data.toModel())
        } else {
            return res as HookahResponse.Error
        }
    }
}