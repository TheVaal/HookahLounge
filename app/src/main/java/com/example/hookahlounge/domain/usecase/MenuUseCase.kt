package com.example.hookahlounge.domain.usecase

import com.example.hookahlounge.data.entity.core.MenuEntity
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toModel
import com.example.hookahlounge.domain.model.Menu
import com.example.hookahlounge.domain.repository.api.MenuRepository
import com.example.hookahlounge.domain.repository.local.MenuDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MenuUseCase @Inject constructor(
    private val menuDbRepository: MenuDbRepository,
    private val menuRepository: MenuRepository,
) {

    suspend fun loadMenuById(menuId: Long): Flow<HookahResponse<Menu>> {
        return menuDbRepository.getMenu(menuId).map {
            HookahResponse.Success(it.toModel())
        }.onStart {
            val response: HookahResponse<MenuEntity> = menuRepository.loadMenuById(menuId)
            if (response is HookahResponse.Success) {
                menuDbRepository.upsertMenu(response.data)
            }
        }
    }

    suspend fun loadMenus(): Flow<HookahResponse<List<Menu>>> {
        return menuDbRepository.getMenus().map { list ->
            HookahResponse.Success(
                list.map { menu ->
                    menu.toModel()
                }
            )
        }.onStart {
            val response: HookahResponse<List<MenuEntity>> = menuRepository.loadMenus()
            if (response is HookahResponse.Success) {
                menuDbRepository.upsertAll(response.data)
            }
        }
    }

    suspend fun postMenu(menu: Menu): HookahResponse<Menu> {

        val res = menuRepository.postMenu(menu.toDto())
            .onSuccess {
                menuDbRepository.upsertMenu(it)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun putMenu(menu: Menu): HookahResponse<Menu> {
        val res = menuRepository.putMenu(menu.toDto())
        return if (res is HookahResponse.Success) {
            menuDbRepository.upsertMenu(res.data)
            HookahResponse.Success(res.data.toModel())
        } else {
            return res as HookahResponse.Error
        }
    }
}