package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.datasource.InOrderDto
import com.example.hookahlounge.data.dto.DataWrapper
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface InOrderApi {

    @GET("api/v1/inOrder")
    suspend fun getInOrder(): Response<DataWrapper<List<InOrderDto>>>

    @GET("api/v1/inOrder/{id}")
    suspend fun getInOrder(@Path("id") id: Int): Response<DataWrapper<InOrderDto>>

    @PATCH("api/v1/inOrder/{id}")
    suspend fun patchInOrder(@Path("id") id: Int, @Body inOrder: InOrderDto): Response<ResponseBody>

    @POST("api/v1/inOrder/{id}")
    suspend fun postInOrder(@Path("id") id: Int, @Body inOrder: InOrderDto): Response<DataWrapper<InOrderDto>>

}