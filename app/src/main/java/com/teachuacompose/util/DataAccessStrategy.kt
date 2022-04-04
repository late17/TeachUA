package com.teachuacompose.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T> performGetFromDB(
    networkCall: suspend () -> Resource<T>
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        val responseStatus = networkCall.invoke()
        emit(responseStatus)
    }

fun <T> performGetFromDB(
    networkCall: suspend (id : Int) -> Resource<T>,
    key : Int
) : LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        val responseStatus = networkCall.invoke(key)
        emit(responseStatus)
    }