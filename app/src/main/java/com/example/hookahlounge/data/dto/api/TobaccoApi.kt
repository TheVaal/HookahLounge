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
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TobaccoApi {

    @GET("api/v1/tobacco")
    suspend fun getTobacco(@Query("page") page: Int): Response<DataWrapper<List<TobaccoDto>>>

    @GET("api/v1/tobacco/{id}")
    suspend fun getTobaccoById(@Path("id") id: Long): Response<DataWrapper<TobaccoDto>>

    @PUT("api/v1/tobacco/{id}")
    suspend fun putTobacco(@Path("id") id: Long, @Body tobacco: TobaccoDto): Response<ResponseBody>

    @POST("api/v1/tobacco")
    suspend fun postTobacco(@Body tobacco: TobaccoDto): Response<DataWrapper<TobaccoDto>>

    @GET("api/v1/loungeTobacco")
    suspend fun getLoungeTobacco(
        @Query("page") page: Int,
        @Query("loungeId[eq]") loungeId: Long
    ): Response<DataWrapper<List<LoungeTobaccoDto>>>

    @GET("api/v1/loungeTobacco/{id}")
    suspend fun getLoungeTobaccoById(@Path("id") id: Long): Response<DataWrapper<LoungeTobaccoDto>>

    @PUT("api/v1/loungeTobacco/{id}")
    suspend fun putLoungeTobacco(@Path("id") id: Long, @Body loungeTobacco: LoungeTobaccoDto): Response<ResponseBody>

    @POST("api/v1/loungeTobacco")
    suspend fun postLoungeTobacco(@Body loungeTobacco: LoungeTobaccoDto): Response<DataWrapper<LoungeTobaccoDto>>


}