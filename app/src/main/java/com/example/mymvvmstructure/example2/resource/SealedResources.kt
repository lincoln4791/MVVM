package com.example.mymvvmstructure.example2.resource

import okhttp3.ResponseBody

sealed class SealedResources<out T> {
    data class Success<out T>(val data:T) : SealedResources<T>()
    data class Failed<out T>(val networkError: Boolean?, val errorCode: Int?, val errorBody: ResponseBody?) : SealedResources<T>()
}