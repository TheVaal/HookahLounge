package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.DataWrapper
import com.example.hookahlounge.data.dto.datasource.MixDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MixApi {

    @GET("api/v1/mixes?includeDetails=true")
    suspend fun getMix(
        @Query("page") page: Int,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<List<MixDto>>>

    @GET("api/v1/mixes?includeDetails=true")
    suspend fun getMixByOrder(
        @Query("page") page: Int,
        @Query("orderId[eq]") orderId: Long,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<List<MixDto>>>

    @GET("api/v1/mixes/{id}?includeDetails=true")
    suspend fun getMixById(
        @Path("id") id: Long,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<MixDto>>

    @PUT("api/v1/mixes/{id}")
    suspend fun putMix(
        @Path("id") id: Long,
        @Body mix: MixDto,
        @Header("Authorization") auth: String
    ): Response<ResponseBody>

    @POST("api/v1/mixes")
    suspend fun postMix(
        @Body mix: MixDto,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<MixDto>>

}