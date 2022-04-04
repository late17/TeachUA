package com.teachuacompose.rest.remote

import com.teachuacompose.util.Resource
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
abstract class BaseDataSource{
    protected suspend fun <T> getResult(call: suspend () -> Response<T>) : Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful){
                val body = response.body()
                if(body!=null) return Resource.success(body)
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception){
            return Resource.error(data = null, message = e.message)
        }
    }
}