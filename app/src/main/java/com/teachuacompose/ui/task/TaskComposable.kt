package com.teachuacompose.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.teachuacompose.R
import com.teachuacompose.app.baseImageUrl
import com.teachuacompose.data.model.dto.challenge.task.Task
import com.teachuacompose.ui.challenges.ChallengePage
import com.teachuacompose.ui.compose.HtmlText
import com.teachuacompose.ui.compose.util.HtmlTextWrapper
import com.teachuacompose.ui.compose.util.ImageAndTitleOnIt
import com.teachuacompose.ui.compose.util.ResourceWrapper
import com.teachuacompose.ui.compose.util.TopBar
import com.teachuacompose.util.Resource

@Composable
fun TaskComposable(
    id: Int,
    onButtonClicked: () -> Unit
) {
    val taskViewModel = hiltViewModel<TaskViewModel>()
    LaunchedEffect(key1 = true) {
        taskViewModel.load(id)
    }
    val task by taskViewModel.task.collectAsState(Resource.loading())

    Column {
        TopBar(
            title = if (task.data == null) "Підключення..." else task.data!!.name,
            buttonIcon = Icons.Filled.ArrowBack,
            onButtonClicked = onButtonClicked
        )
        TaskPage(task) { taskViewModel.load(id) }
    }

}

@Composable
fun TaskPage(task: Resource<Task>, function: () -> Unit) {
    ResourceWrapper(resource = task, onReloadButtonClick = function) {
        val rememberScrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(5.dp)
                .verticalScroll(rememberScrollState)
        ) {
            ImageAndTitleOnIt(picture = task.data?.picture ?: "", title = task.data?.name ?: "")
            val headerAnnotatedString = task.data?.headerText ?: ""
            HeaderText(string = headerAnnotatedString)

            val annotatedString =  task.data?.description ?: ""
            HtmlTextWrapper(annotatedString)
        }

    }
}

@Composable
fun HeaderText(string: String) {
    Spacer(modifier = Modifier.height(10.dp))
    HtmlTextWrapper(string = string)
    Spacer(modifier = Modifier.height(5.dp))
    Divider(color = MaterialTheme.colors.onBackground, thickness = 1.dp)
    Spacer(modifier = Modifier.height(5.dp))
}