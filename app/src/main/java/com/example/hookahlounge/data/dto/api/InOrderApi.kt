package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.DataWrapper
import com.example.hookahlounge.data.dto.datasource.InOrderDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface InOrderApi {

    @GET("api/v1/inOrder?includeDetails=true")
    suspend fun getInOrder(
        @Query("page") page: Int,
        @Query("orderId[eq]") orderId: Long,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<List<InOrderDto>>>

    @GET("api/v1/inOrder/{id}?includeDetails=true")
    suspend fun getInOrder(
        @Path("id") id: Long,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<InOrderDto>>

    @PUT("api/v1/inOrder/{id}")
    suspend fun putInOrder(
        @Path("id") id: Long,
        @Body inOrder: InOrderDto,
        @Header("Authorization") auth: String): Response<ResponseBody>

    @POST("api/v1/inOrder/")
    suspend fun postInOrder(
        @Body inOrder: InOrderDto,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<InOrderDto>>

}