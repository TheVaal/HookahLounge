package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.datasource.SessionDto
import com.example.hookahlounge.data.dto.DataWrapper
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SessionApi {

    @GET("api/v1/session")
    suspend fun getSessions(
        @Query("page") page: Int = 1,
        @Query("pageSize")pageSize: Int
    ): Response<DataWrapper<List<SessionDto>>>

    @GET("api/v1/session/{id}")
    suspend fun getSession(@Path("id") id: Int): Response<DataWrapper<SessionDto>>

    @PATCH("api/v1/session/{id}")
    suspend fun patchSession(@Path("id") id: Int, @Body session: SessionDto): Response<ResponseBody>

    @POST("api/v1/session/{id}")
    suspend fun postSession(@Path("id") id: Int, @Body session: SessionDto): Response<DataWrapper<SessionDto>>

}