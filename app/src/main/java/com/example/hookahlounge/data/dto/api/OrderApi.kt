package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.datasource.OrderDto
import com.example.hookahlounge.data.dto.DataWrapper
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderApi {
    @GET("api/v1/order")
    suspend fun getOrders(
        @Query("page") page: Int = 1,
        @Query("pageSize")pageSize: Int
    ): Response<DataWrapper<List<OrderDto>>>

    @GET("api/v1/order/{id}")
    suspend fun getOrder(@Path("id") id: Int): Response<DataWrapper<OrderDto>>

    @PATCH("api/v1/order/{id}")
    suspend fun patchOrder(@Path("id") id: Int, @Body order: OrderDto): Response<ResponseBody>

    @POST("api/v1/order/{id}")
    suspend fun postOrder(@Path("id") id: Int, @Body order: OrderDto): Response<DataWrapper<OrderDto>>

}