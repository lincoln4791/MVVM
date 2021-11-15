package com.example.mymvvmstructure.example2.repository

import com.example.mymvvmstructure.example2.resource.SealedResources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun<T> safeApiCall(
        apiCall : suspend ()->T
    ) : SealedResources<T> {
        return withContext(Dispatchers.IO){
            try {
                SealedResources.Success(apiCall.invoke())
            }
            catch (throwable : Throwable){
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