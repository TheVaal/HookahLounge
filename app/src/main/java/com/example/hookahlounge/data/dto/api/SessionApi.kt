package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.DataWrapper
import com.example.hookahlounge.data.dto.datasource.SessionDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface SessionApi {

    @GET("api/v1/session")
    suspend fun getSessions(
        @Query("page") page: Int = 1,
        @Query("pageSize")pageSize: Int,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<List<SessionDto>>>

    @GET("api/v1/session/{id}")
    suspend fun getSession(
        @Path("id") id: Long,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<SessionDto>>

    @PUT("api/v1/session/{id}")
    suspend fun putSession(
        @Path("id") id: Long,
        @Body session: SessionDto,
        @Header("Authorization") auth: String
    ): Response<ResponseBody>

    @POST("api/v1/session")
    suspend fun postSession(
        @Body session: SessionDto,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<SessionDto>>

}