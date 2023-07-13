package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.DataWrapper
import com.example.hookahlounge.data.dto.datasource.ManufacturerDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ManufacturerApi {

    @GET("api/v1/manufacturers")
    suspend fun getManufacturer(
        @Query("page") page: Int,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<List<ManufacturerDto>>>

    @GET("api/v1/manufacturers/{id}")
    suspend fun getManufacturerById(
        @Path("id") id: Long,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<ManufacturerDto>>

    @PUT("api/v1/manufacturers/{id}")
    suspend fun putManufacturer(
        @Path("id") id: Long,
        @Body manufacturer: ManufacturerDto,
        @Header("Authorization") auth: String): Response<ResponseBody>

    @POST("api/v1/manufacturers")
    suspend fun postManufacturer(
        @Body manufacturer: ManufacturerDto,
        @Header("Authorization") auth: String
    ): Response<DataWrapper<ManufacturerDto>>

}