package com.example.hookahlounge.data.repository.api

import androidx.datastore.core.DataStore
import com.example.hookahlounge.data.datastore.UserPreference
import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.MenuDto
import com.example.hookahlounge.data.entity.core.MenuEntity
import com.example.hookahlounge.data.mappers.toEntity
import com.example.hookahlounge.domain.repository.api.MenuRepository
import com.example.hookahlounge.domain.util.HookahResponse
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
    private val dataStore: DataStore<UserPreference>,
) : MenuRepository {
    override suspend fun loadMenus(): HookahResponse<List<MenuEntity>> {
        return try {
            val tables = mutableListOf<MenuDto>()
            loadPage(
                addAll = { responseData: List<MenuDto> ->
                    tables.addAll(responseData)
                }
            )
            HookahResponse.Success(data = tables.map {
                it.toEntity()
            })

        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    private suspend fun loadPage(
        addAll: (List<MenuDto>) -> (Unit),
        currentPage: Int = 1,
    ) {
        val response = api.getMenus(
            currentPage,
            "Bearer ${dataStore.data.first().token}"
        )
        if (response.isSuccessful) {
            val responseMetadata = response.body()!!.meta
            addAll(response.body()!!.data)
            if (responseMetadata.currentPage!! < responseMetadata.lastPage!!) {
                loadPage(
                    addAll = addAll,
                    currentPage = currentPage.plus(1)
                )
            }
        }
    }

    override suspend fun loadMenuById(menuId: Long): HookahResponse<MenuEntity> {
        return try {
            val response = api.getMenuById(
                menuId,
                "Bearer ${dataStore.data.first().token}"
            )
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntity())
            } else {
                HookahResponse.Error("Unable to reach api host")
            }
        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    override suspend fun putMenu(menu: MenuDto): HookahResponse<MenuEntity> {
        val response = api.putMenu(
            menu.id,
            menu,
            "Bearer ${dataStore.data.first().token}"
        )
        return if (response.isSuccessful){
            HookahResponse.Success(menu.toEntity())
        } else {
            HookahResponse.Error("Put data error")
        }
    }

    override suspend fun postMenu(menu: MenuDto): HookahResponse<MenuEntity> {
        return try {
            val response = api.postMenu(
                menu,
                "Bearer ${dataStore.data.first().token}"
            )
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntity())
            } else {
                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: HttpException){
            HookahResponse.Error(e.message())
        }
    }
}