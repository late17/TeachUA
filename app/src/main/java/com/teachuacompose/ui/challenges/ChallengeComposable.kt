package com.teachuacompose.ui.challenges

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.sharp.Favorite
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.teachuacompose.data.model.uiData.challenge.ChallengeUi
import com.teachuacompose.util.Resource
import com.teachuacompose.R
import com.teachuacompose.app.baseImageUrl
import com.teachuacompose.data.model.dto.challenges.ChallengeItem
import com.teachuacompose.ui.compose.HtmlText
import com.teachuacompose.ui.compose.util.*


@Composable
fun Challenges(
    title: String,
    openDrawer: () -> Unit,
    onClick: (id: Int) -> Unit
) {
    val viewModel = hiltViewModel<ChallengesViewModel>()
    LaunchedEffect(key1 = true) {
        viewModel.load()
    }
    val challenges by viewModel.challenges.collectAsState()

    Column {
        TopBar(
            title = title,
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        ResourceWrapper(
            resource = challenges,
            onReloadButtonClick = {
                viewModel.load()

            },
        ) {
            ChallengesList(challenges = challenges.data!!, onClick)
        }
    }

}

@Composable
fun ChallengesList(challenges: ArrayList<ChallengeItem>, onClick: (id: Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp)
    ) {
        items(challenges) {
            Row(modifier = Modifier
                .clickable { onClick(it.id) }
                .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Sharp.Favorite, contentDescription = "")
                Spacer(Modifier.width(15.dp))
                Text(
                    text = it.name,
                    fontSize = 25.sp
                )

            }
            Spacer(Modifier.height(15.dp))

        }
    }
}

@Composable
fun Challenge(
    id: Int,
    navigateToTask: (Int) -> Unit,
    onButtonClicked: () -> Unit,
) {

    val challengeViewModel = hiltViewModel<ChallengeViewModel>()
    LaunchedEffect(key1 = true) {
        challengeViewModel.load(id)
    }
    val challenge by challengeViewModel.challenge.collectAsState(Resource.loading())

    Column {
        TopBar(
            title = if (challenge.data == null) "Підключення..." else challenge.data!!.title,
            buttonIcon = Icons.Filled.ArrowBack,
            onButtonClicked = onButtonClicked
        )
        ChallengePage(challenge, navigateToTask) { challengeViewModel.load(id) }
    }
}

@Composable
fun ChallengePage(
    challenge: Resource<ChallengeUi>,
    navigateToTask: (Int) -> Unit,
    onClick: () -> Unit) {
    ResourceWrapper(resource = challenge, onReloadButtonClick = { onClick() }) {

        val rememberScrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(5.dp)
                .verticalScroll(rememberScrollState)
        ) {
            ImageAndTitleOnIt(challenge.data?.picture ?: "", challenge.data?.title ?: "")
            ShowLinks()
            HtmlTextWrapper(challenge.data?.description ?: "")
            BottomTasks(challenge, navigateToTask)
        }
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomTasks(challenge: Resource<ChallengeUi>, navigateToTask: (Int) -> Unit) {
    challenge.data?.let {
        LazyRow(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            items(it.tasks) { task ->
                Card(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .height(215.dp)
                        .padding(10.dp)
                        .width(230.dp),
                    onClick = { navigateToTask(task.id) }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Surface(
                            color = MaterialTheme.colors.surface,
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                        ) {
                            AsyncImage(
                                model = baseImageUrl + task.picture,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        val annotatedString = task.name
                        HtmlText(text = annotatedString,
                            modifier = Modifier.padding(4.dp),
                            maxLines = 3)
                    }

                }
            }
        }
    }
}


