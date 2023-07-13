package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.DataWrapper
import com.example.hookahlounge.data.dto.datasource.OrderDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderApi {
    @GET("api/v1/order")
    suspend fun getOrders(
        @Query("page") page: Int = 1,
        @Query("pageSize")pageSize: Int,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<List<OrderDto>>>

    @GET("api/v1/order/{id}?includeDetails=true")
    suspend fun getOrder(
        @Path("id") id: Long,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<OrderDto>>

    @PUT("api/v1/order/{id}")
    suspend fun putOrder(
        @Path("id") id: Long,
        @Body order: OrderDto,
        @Header("Authorization") auth: String
    ): Response<ResponseBody>

    @POST("api/v1/order/")
    suspend fun postOrder(
        @Body order: OrderDto,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<OrderDto>>

}