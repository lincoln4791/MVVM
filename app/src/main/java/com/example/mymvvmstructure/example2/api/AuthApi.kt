package com.example.mymvvmstructure.example2.api

import com.example.mymvvmstructure.example2.dataClass.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("api/v1/login")
    suspend fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ) : LoginResponse
}