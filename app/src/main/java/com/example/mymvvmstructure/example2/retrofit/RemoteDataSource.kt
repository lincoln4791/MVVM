package com.example.mymvvmstructure.example2.retrofit

import com.example.mymvvmstructure.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource {
    companion object{
       const val BASE_URL = "http://sandbox.emdexapi.com/"
       //const val BASE_URL = "sandbox.emdexapi.com/"
        /*private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
            .build()*/

        private val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val interprator = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val okHttpClient = OkHttpClient
            .Builder()
            .connectTimeout(100,TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .writeTimeout(100,TimeUnit.SECONDS)
            .addInterceptor(interprator)
            .build()
    }

    fun<Api> buildApi(
        api : Class<Api>
    ):Api{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            /*.client(
                OkHttpClient.Builder().also {
                   if(BuildConfig.DEBUG){
                       val logging = HttpLoggingInterceptor()
                       logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                       it.addInterceptor(logging)
                   }
                }.build()
            )*/
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(api)
    }
}