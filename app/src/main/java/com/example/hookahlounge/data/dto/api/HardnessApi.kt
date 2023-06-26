package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.datasource.HardnessDto
import com.example.hookahlounge.data.dto.DataWrapper
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface HardnessApi {

    @GET("api/v1/hardness")
    suspend fun getHardness(@Query("page") page: Int): Response<DataWrapper<List<HardnessDto>>>

    @GET("api/v1/hardness/{id}")
    suspend fun getHardnessById(@Path("id") id: Long): Response<DataWrapper<HardnessDto>>

    @PUT("api/v1/hardness/{id}")
    suspend fun putHardness(@Path("id") id: Long, @Body hardness: HardnessDto): Response<ResponseBody>

    @POST("api/v1/hardness")
    suspend fun postHardness(@Body hardness: HardnessDto): Response<DataWrapper<HardnessDto>>

}