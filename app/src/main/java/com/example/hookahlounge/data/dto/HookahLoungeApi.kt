package com.example.hookahlounge.data.dto

import com.example.hookahlounge.data.dto.api.HardnessApi
import com.example.hookahlounge.data.dto.api.HookahApi
import com.example.hookahlounge.data.dto.api.InOrderApi
import com.example.hookahlounge.data.dto.api.LoungeApi
import com.example.hookahlounge.data.dto.api.ManufacturerApi
import com.example.hookahlounge.data.dto.api.MenuApi
import com.example.hookahlounge.data.dto.api.MixApi
import com.example.hookahlounge.data.dto.api.OrderApi
import com.example.hookahlounge.data.dto.api.SessionApi
import com.example.hookahlounge.data.dto.api.TableApi
import com.example.hookahlounge.data.dto.api.TobaccoApi
import com.example.hookahlounge.data.dto.datasource.UserDto
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query


interface HookahLoungeApi : HardnessApi, HookahApi, InOrderApi, OrderApi, LoungeApi,
    TableApi, SessionApi, MixApi, ManufacturerApi, TobaccoApi, MenuApi {
    companion object {
        const val BASE_URL = "https://f8ac-5-248-101-104.ngrok-free.app"
    }

    @POST("api/login")
    suspend fun getToken(
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("device_name") deviceName: String = "android"
    ): Response<UserDto>

}