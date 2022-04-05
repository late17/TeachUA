package com.teachuacompose.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

suspend fun <T> performGetFromRemote(
    networkCall: suspend () -> Resource<T>
) : Resource<T> {
    return networkCall.invoke()
}

