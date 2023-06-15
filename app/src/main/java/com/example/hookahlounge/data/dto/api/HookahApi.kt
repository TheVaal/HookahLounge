package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.datasource.HookahDto
import com.example.hookahlounge.data.dto.DataWrapper
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface HookahApi {

    @GET("api/v1/hookah")
    suspend fun getHookahs(): Response<DataWrapper<List<HookahDto>>>

    @GET("api/v1/hookah/{id}")
    suspend fun getHookah(@Path("id") id: Int): Response<DataWrapper<HookahDto>>

    @PATCH("api/v1/hookah/{id}")
    suspend fun patchHookah(@Path("id") id: Int, @Body hookah: HookahDto): Response<ResponseBody>

    @POST("api/v1/hookah/{id}")
    suspend fun postHookah(@Path("id") id: Int, @Body hookah: HookahDto): Response<DataWrapper<HookahDto>>

}