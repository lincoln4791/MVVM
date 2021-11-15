package com.example.mymvvmstructure.example1

import com.example.mymvvmstructure.example1.modelClasses.Status

/**
 * Created by Soumik on 11,October,2020
 * ITmedicus,
 * Dhaka, Bangladesh.
 */
data class Resource<T> (val status: Status, val data:T?, val error:String?){

    companion object {

        fun <T> success(data:T): Resource<T> {
            return Resource(Status.SUCCESS,data,null)
        }

        fun <T> error(message:String?): Resource<T> {
            return Resource(Status.ERROR,null,message)
        }

        fun <T> loading(): Resource<T> = Resource(Status.LOADING,null,null)
    }
}