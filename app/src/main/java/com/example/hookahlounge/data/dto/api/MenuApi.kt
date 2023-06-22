package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.DataWrapper
import com.example.hookahlounge.data.dto.datasource.LoungeMenuDto
import com.example.hookahlounge.data.dto.datasource.MenuDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MenuApi {

    @GET("api/v1/menu")
    suspend fun getMenus(@Query("page") page: Int): Response<DataWrapper<List<MenuDto>>>

    @GET("api/v1/menu/{id}")
    suspend fun getMenuById(@Path("id") id: Long): Response<DataWrapper<MenuDto>>

    @PUT("api/v1/menu/{id}")
    suspend fun putMenu(@Path("id") id: Long, @Body tobacco: MenuDto): Response<ResponseBody>

    @POST("api/v1/menu")
    suspend fun postMenu(@Body tobacco: MenuDto): Response<DataWrapper<MenuDto>>

    @GET("api/v1/loungeMenu")
    suspend fun getLoungeMenu(@Query("page") page: Int, @Query("loungeId[eq]") loungeId: Long): Response<DataWrapper<List<LoungeMenuDto>>>

    @GET("api/v1/loungeMenu/{id}")
    suspend fun getLoungeMenuById(@Path("id") id: Long): Response<DataWrapper<LoungeMenuDto>>

    @PUT("api/v1/loungeMenu/{id}")
    suspend fun putLoungeMenu(@Path("id") id: Long, @Body loungeMenu: LoungeMenuDto): Response<ResponseBody>

    @POST("api/v1/loungeMenu")
    suspend fun postLoungeMenu(@Body loungeMenu: LoungeMenuDto): Response<DataWrapper<LoungeMenuDto>>

}