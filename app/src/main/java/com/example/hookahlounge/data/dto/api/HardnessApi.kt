package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.datasource.HardnessDto
import com.example.hookahlounge.data.dto.DataWrapper
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface HardnessApi {

    @GET("api/v1/hardness")
    suspend fun getHardness(): Response<DataWrapper<List<HardnessDto>>>

    @GET("api/v1/hardness/{id}")
    suspend fun getHardness(@Path("id") id: Int): Response<DataWrapper<HardnessDto>>

    @PATCH("api/v1/hardness/{id}")
    suspend fun patchHardness(@Path("id") id: Int, @Body hardness: HardnessDto): Response<ResponseBody>

    @POST("api/v1/hardness/{id}")
    suspend fun postHardness(@Path("id") id: Int, @Body hardness: HardnessDto): Response<DataWrapper<HardnessDto>>

}