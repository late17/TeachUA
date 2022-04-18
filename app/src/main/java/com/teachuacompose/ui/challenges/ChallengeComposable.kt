package com.teachuacompose.ui.challenges

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.teachuacompose.data.model.dto.challenges.Challenges
import com.teachuacompose.data.model.uiData.challenge.ChallengeUi
import com.teachuacompose.ui.compose.util.ResourceWrapper
import com.teachuacompose.ui.compose.util.ShowLinks
import com.teachuacompose.ui.compose.util.TopBar
import com.teachuacompose.util.Resource
import com.teachuacompose.R
import com.teachuacompose.app.baseImageUrl
import com.teachuacompose.ui.compose.HtmlText


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
fun ChallengesList(challenges: Challenges, onClick: (id: Int) -> Unit) {
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
                    text = it.title,
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
        ChallengeContent(challenge) { challengeViewModel.load(id) }
    }
}

@Composable
fun ChallengeContent(challenge: Resource<ChallengeUi>, onClick: () -> Unit) {
    ResourceWrapper(resource = challenge, onReloadButtonClick = { onClick() }) {
        LazyColumn(modifier = Modifier.padding(5.dp)) {
            item {
                Column {
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.height(190.dp),
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = baseImageUrl + challenge.data?.picture,
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize(),
                                colorFilter = ColorFilter.tint(
                                    color = Color(R.color.tint),
                                    blendMode = BlendMode.Darken
                                )
                            )
                            Text(
                                text = challenge.data?.title ?: "",
                                color = Color.White,
                                style = MaterialTheme.typography.h4,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                    ShowLinks()

                    val annotatedString = challenge.data?.description ?: ""
                    HtmlText(text = annotatedString, modifier = Modifier.padding(4.dp))


                }
            }

        }
    }
}


//@Composable
//fun ChallengeCard(id : Int) {
//    val challengeViewModel = hiltViewModel<ChallengeViewModel>()
//    LaunchedEffect(key1 = true) {
//        challengeViewModel.load(id)
//    }
//    val challenge by challengeViewModel.challenge.collectAsState(Resource.loading())
//
//    Surface(
//        modifier = Modifier.size(width = 300.dp, height = 300.dp),
//        shape = MaterialTheme.shapes.large,
//        color = MaterialTheme.colors.surface
//    ) {
//        Column() {
//            Box(modifier = Modifier.size(height = 200.dp, width = 300.dp), contentAlignment = Alignment.Center) {
//                AsyncImage(
//                    model = baseImageUrl + challenge.data?.picture,
//                    contentDescription = "",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxSize(),
//                    colorFilter = ColorFilter.tint(
//                        color = Color(0x6F000000), blendMode = BlendMode.Darken)
//                )
//
//                Text(
//                    text = challenge.data?.title ?: "",
//                    color = Color.White,
//                    style = MaterialTheme.typography.h4,
//                    textAlign = TextAlign.Center
//                    )
//            }
//            Surface(modifier = Modifier.fillMaxSize()) {
//                val text = challenge.data?.description ?: buildAnnotatedString { }
//                Text(text = text)
//            }
//        }
//
//    }
//}
//

