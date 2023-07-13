package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.DataWrapper
import com.example.hookahlounge.data.dto.datasource.HookahDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface HookahApi {

    @PUT("api/v1/hookah/{id}")
    suspend fun putHookah(
        @Path("id") id: Long,
        @Body hookah: HookahDto,
        @Header("Authorization") auth: String
    ): Response<ResponseBody>

    @POST("api/v1/hookah/")
    suspend fun postHookah(
        @Body hookah: HookahDto,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<HookahDto>>

}