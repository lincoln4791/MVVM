package com.example.mymvvmstructure.example2.repository

import android.util.Log
import com.example.mymvvmstructure.example2.resource.SealedResources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun<T> safeApiCall(
        apiCall : suspend ()->T
    ) : SealedResources<T> {
        Log.d("tag","ApiCalled")
        return withContext(Dispatchers.IO){
            try {
                Log.d("tag","try o invocke")
                SealedResources.Success(apiCall.invoke())
            }
            catch (throwable : Throwable){
                Log.d("tag","invoke failed ${throwable.printStackTrace()}")
                Log.d("tag","invoke failed ${throwable.message}")
                Log.d("tag","invoke failed ${throwable.localizedMessage}")
               when(throwable){
                   is HttpException->{
                       SealedResources.Failed(false,throwable.code(),
                           throwable.response()?.errorBody())
                   }
                   else->{
                       SealedResources.Failed(true,null,null)
                   }
               }
            }
        }
    }
}