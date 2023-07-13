package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.datasource.TableDto
import com.example.hookahlounge.data.dto.DataWrapper
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TableApi {
    @GET("api/v1/tables")
    suspend fun getTables(
        @Query("page") page: Int,
        @Query("loungeId[eq]") loungeId: Long,
        @Header("Authorization") auth: String): Response<DataWrapper<List<TableDto>>>

    @GET("api/v1/tables/{id}")
    suspend fun getTable(
        @Path("id") id: Long,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<TableDto>>

    @POST("api/v1/tables/")
    suspend fun postTable(
        @Body table: TableDto,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<TableDto>>

    @PUT("api/v1/tables/{id}")
    suspend fun putTable(
        @Path("id") id: Long,
        @Body table: TableDto,
        @Header("Authorization") auth: String): Response<ResponseBody>

}