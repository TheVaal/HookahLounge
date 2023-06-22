package com.example.hookahlounge.data.repository.api

import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.LoungeMenuDto
import com.example.hookahlounge.data.entity.projection.LoungeMenuWithFields
import com.example.hookahlounge.data.mappers.toEntityWithFields
import com.example.hookahlounge.domain.repository.api.LoungeMenuRepository
import com.example.hookahlounge.domain.util.HookahResponse
import retrofit2.HttpException
import javax.inject.Inject

class LoungeMenuRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
) : LoungeMenuRepository {
    override suspend fun loadLoungeMenuByLoungeId(loungeId: Long): HookahResponse<List<LoungeMenuWithFields>> {
        return try {
            val loungeMenus = mutableListOf<LoungeMenuDto>()
            loadPage(
                addAll = { responseData: List<LoungeMenuDto> ->
                    loungeMenus.addAll(responseData)
                },
                loungeId = loungeId
            )
            HookahResponse.Success(data = loungeMenus.map {
                it.toEntityWithFields()
            })

        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    private suspend fun loadPage(
        addAll: (List<LoungeMenuDto>) -> (Unit),
        currentPage: Int = 1,
        loungeId: Long,
    ) {
        val response = api.getLoungeMenu(currentPage, loungeId)
        if (response.isSuccessful) {
            val responseMetadata = response.body()!!.meta
            addAll(response.body()!!.data)
            if (responseMetadata.currentPage!! < responseMetadata.lastPage!!) {
                loadPage(
                    addAll = addAll,
                    currentPage = currentPage.plus(1),
                    loungeId = loungeId
                )
            }
        }
    }

    override suspend fun loadLoungeMenuById(loungeMenuId: Long): HookahResponse<LoungeMenuWithFields> {
        return try {
            val response = api.getLoungeMenuById(loungeMenuId)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntityWithFields())
            } else {
                HookahResponse.Error("Unable to reach api host")
            }
        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    override suspend fun putLoungeMenu(loungeMenu: LoungeMenuDto): HookahResponse<LoungeMenuWithFields> {
        val response = api.putLoungeMenu(loungeMenu.id, loungeMenu)
        return if (response.isSuccessful){
            HookahResponse.Success(loungeMenu.toEntityWithFields())
        } else {
            HookahResponse.Error("Put data error")
        }
    }

    override suspend fun postLoungeMenu(loungeMenu: LoungeMenuDto): HookahResponse<LoungeMenuWithFields> {
        return try {
            val response = api.postLoungeMenu(loungeMenu)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntityWithFields())
            } else {

                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: HttpException){
            HookahResponse.Error(e.message())
        }
    }

}