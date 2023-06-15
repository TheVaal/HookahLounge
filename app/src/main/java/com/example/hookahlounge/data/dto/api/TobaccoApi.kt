package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.DataWrapper
import com.example.hookahlounge.data.dto.datasource.LoungeTobaccoDto
import com.example.hookahlounge.data.dto.datasource.TobaccoDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TobaccoApi {

    @GET("api/v1/tobacco")
    suspend fun getTobacco(): Response<DataWrapper<List<TobaccoDto>>>

    @GET("api/v1/tobacco/{id}")
    suspend fun getTobacco(@Path("id") id: Int): Response<DataWrapper<TobaccoDto>>

    @PATCH("api/v1/tobacco/{id}")
    suspend fun patchTobacco(@Path("id") id: Int, @Body tobacco: TobaccoDto): Response<ResponseBody>

    @POST("api/v1/tobacco/{id}")
    suspend fun postTobacco(@Path("id") id: Int, @Body tobacco: TobaccoDto): Response<DataWrapper<TobaccoDto>>

    @GET("api/v1/loungeTobacco")
    suspend fun getLoungeTobacco(): Response<DataWrapper<List<LoungeTobaccoDto>>>

    @GET("api/v1/loungeTobacco/{id}")
    suspend fun getLoungeTobacco(@Path("id") id: Int): Response<DataWrapper<LoungeTobaccoDto>>

    @PATCH("api/v1/loungeTobacco/{id}")
    suspend fun patchLoungeTobacco(@Path("id") id: Int, @Body loungeTobacco: LoungeTobaccoDto): Response<ResponseBody>

    @POST("api/v1/loungeTobacco/{id}")
    suspend fun postLoungeTobacco(@Path("id") id: Int, @Body loungeTobacco: LoungeTobaccoDto): Response<DataWrapper<LoungeTobaccoDto>>


}