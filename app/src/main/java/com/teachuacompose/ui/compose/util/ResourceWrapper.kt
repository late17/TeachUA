package com.teachuacompose.ui.compose.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.teachuacompose.util.Resource


@Composable
fun <T>  ResourceWrapper(
    resource: Resource<T>?,
    loadingContent: @Composable () -> Unit = { DefaultLoadingContent() },
    onReloadButtonClick : () -> Unit,
    errorContent: @Composable (msg : String?) -> Unit = { errorMessage ->
        DefaultErrorContent(errorMessage) { onReloadButtonClick() }
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



class stringProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "lkjsdf;a"
    )
}

class EmptyFunctionProvider : PreviewParameterProvider<() -> Unit>{
    override val values: Sequence<() -> Unit> =
        sequenceOf(
            {}
        )

}

@Composable
fun DefaultErrorContent(
    errorMessage: String?,
    function: () -> Unit
){
    Surface(contentColor = Color.Black, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(com.teachuacompose.app.di.errorMessage)
                IconButton(onClick = {function()}) {
                    Icon(imageVector = Icons.Filled.Refresh, contentDescription = "")
                }
            }
        }
    }


}