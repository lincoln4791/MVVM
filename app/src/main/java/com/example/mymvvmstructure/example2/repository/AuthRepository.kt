package com.example.mymvvmstructure.example2.repository

import com.example.mymvvmstructure.example2.api.AuthApi

class AuthRepository(
    private val api : AuthApi
) : BaseRepository() {



    suspend fun login(email:String, password:String)=safeApiCall {
        api.login(email,password)
    }
}