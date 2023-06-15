package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.datasource.MixDto
import com.example.hookahlounge.data.dto.DataWrapper
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MixApi {

    @GET("api/v1/mixes")
    suspend fun getMix(): Response<DataWrapper<List<MixDto>>>

    @GET("api/v1/mixes/{id}")
    suspend fun getMix(@Path("id") id: Int): Response<DataWrapper<MixDto>>

    @PATCH("api/v1/mixes/{id}")
    suspend fun patchMix(@Path("id") id: Int, @Body mix: MixDto): Response<ResponseBody>

    @POST("api/v1/mixes/{id}")
    suspend fun postMix(@Path("id") id: Int, @Body mix: MixDto): Response<DataWrapper<MixDto>>

}