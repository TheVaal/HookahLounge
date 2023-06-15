package com.example.hookahlounge.data.dto.api

import com.example.hookahlounge.data.dto.DataWrapper
import com.example.hookahlounge.data.dto.datasource.ManufacturerDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ManufacturerApi {

    @GET("api/v1/manufacturers")
    suspend fun getManufacturer(): Response<DataWrapper<List<ManufacturerDto>>>

    @GET("api/v1/manufacturers/{id}")
    suspend fun getManufacturer(@Path("id") id: Int): Response<DataWrapper<ManufacturerDto>>

    @PATCH("api/v1/manufacturers/{id}")
    suspend fun patchManufacturer(@Path("id") id: Int, @Body manufacturer: ManufacturerDto): Response<ResponseBody>

    @POST("api/v1/manufacturers/{id}")
    suspend fun postManufacturer(@Path("id") id: Int, @Body manufacturer: ManufacturerDto): Response<DataWrapper<ManufacturerDto>>

}