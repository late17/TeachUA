package com.teachuacompose.ui.compose

import android.util.Log
import android.util.Log.INFO
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.teachuacompose.di.errorMessage
import com.teachuacompose.util.Resource
import java.util.logging.Level.INFO

@Composable
fun <T>  ResourceWrapper(
    resource: Resource<T>?,
    loadingContent: @Composable () -> Unit = { DefaultLoadingContent() },
    errorContent: @Composable (msg : String?) -> Unit = { errorMessage ->
        DefaultErrorContent(errorMessage )
    },
    content : @Composable (T) -> Unit
)
{
    when (resource?.status){
        Resource.Status.LOADING -> {
            loadingContent()
        }
        Resource.Status.SUCCESS -> {
            content(resource.data!!)
        } else -> {
            errorContent(msg = resource?.message)
        }
    }
}

@Composable
fun DefaultLoadingContent(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
    }
}

@Composable
fun DefaultErrorContent(msg: String?){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Row() {
            Text( errorMessage )
        }
    }
}