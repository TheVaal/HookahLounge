package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.DataWrapper
import com.example.hookahlounge.data.dto.datasource.LoungeDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface LoungeApi {
    @GET("api/v1/lounges")
    suspend fun getLounges(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int,
        @Header("Authorization") auth: String,
    ): Response<DataWrapper<List<LoungeDto>>>

    @GET("api/v1/lounges/{id}?includeDetails=true")
    suspend fun getLounge(
        @Path("id") id: Long,
        @Header("Authorization") auth: String,
    ): Response<DataWrapper<LoungeDto>>

    @POST("api/v1/lounges")
    suspend fun postLounge(
        @Body lounge: LoungeDto,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<LoungeDto>>

    @PUT("api/v1/lounges/{id}")
    suspend fun putLounge(
        @Path("id") id: Long,
        @Body lounge: LoungeDto,
        @Header("Authorization") auth: String,
    ): Response<ResponseBody>

}