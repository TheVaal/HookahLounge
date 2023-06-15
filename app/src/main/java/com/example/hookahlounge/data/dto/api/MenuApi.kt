package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.DataWrapper
import com.example.hookahlounge.data.dto.datasource.LoungeMenuDto
import com.example.hookahlounge.data.dto.datasource.MenuDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MenuApi {

    @GET("api/v1/tobacco")
    suspend fun getMenu(): Response<DataWrapper<List<MenuDto>>>

    @GET("api/v1/tobacco/{id}")
    suspend fun getMenu(@Path("id") id: Int): Response<DataWrapper<MenuDto>>

    @PATCH("api/v1/tobacco/{id}")
    suspend fun patchMenu(@Path("id") id: Int, @Body tobacco: MenuDto): Response<ResponseBody>

    @POST("api/v1/tobacco/{id}")
    suspend fun postMenu(@Path("id") id: Int, @Body tobacco: MenuDto): Response<DataWrapper<MenuDto>>

    @GET("api/v1/loungeMenu")
    suspend fun getLoungeMenu(): Response<DataWrapper<List<LoungeMenuDto>>>

    @GET("api/v1/loungeMenu/{id}")
    suspend fun getLoungeMenu(@Path("id") id: Int): Response<DataWrapper<LoungeMenuDto>>

    @PATCH("api/v1/loungeMenu/{id}")
    suspend fun patchLoungeMenu(@Path("id") id: Int, @Body loungeMenu: LoungeMenuDto): Response<ResponseBody>

    @POST("api/v1/loungeMenu/{id}")
    suspend fun postLoungeMenu(@Path("id") id: Int, @Body loungeMenu: LoungeMenuDto): Response<DataWrapper<LoungeMenuDto>>

}