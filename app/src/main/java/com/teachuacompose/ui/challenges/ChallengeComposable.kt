package com.teachuacompose.ui.challenges

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.teachuacompose.di.baseImageUrl
import com.teachuacompose.dto.Challenge
import com.teachuacompose.dto.Challenges
import com.teachuacompose.ui.compose.ResourceWrapper
import com.teachuacompose.ui.compose.TopBar
import com.teachuacompose.util.Resource
import io.noties.markwon.Markwon
import io.noties.markwon.html.HtmlPlugin

@Composable
fun Challenges(
    title: String,
    openDrawer: () -> Unit,
    challenges: State<Resource<Challenges>>,
    onClick: (id: Int) -> Unit
){
    Column {
        TopBar(
            title = title,
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        ResourceWrapper(
            resource = challenges.value,
        ) { ChallengesList(challenges = challenges.value.data!!, onClick)}
    }
}

@Composable
fun ChallengesList(challenges: Challenges, onClick : (id : Int) -> Unit) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(5.dp)
        ){
            items(challenges){
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)) {
                    Text(
                        text = it.title,
                        modifier = Modifier.clickable { onClick(it.id) },
                        fontSize = 20.sp
                    )
                }
                Spacer(Modifier.height(5.dp))

            }
        }
}

@Composable
fun Challenge(
    challenge: State<Resource<Challenge>>,
    viewModel: ChallengeViewModel,
    onButtonClicked: () -> Unit,
) {
        Column {
            TopBar(
                title = if (challenge.value.data == null) "Підключення..." else challenge.value.data!!.title,
                buttonIcon = Icons.Filled.ArrowBack,
                onButtonClicked =  onButtonClicked
            )
            ResourceWrapper(resource = challenge.value) {

                LazyColumn(modifier = Modifier.padding(5.dp)) {
                    item {
                        Surface(
                            color = MaterialTheme.colors.background,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column {
                                Surface(
                                    shape = MaterialTheme.shapes.medium,
                                    color = MaterialTheme.colors.primaryVariant,
                                    modifier = Modifier.height(190.dp)
                                ) {
                                    AsyncImage(
                                        model = baseImageUrl + challenge.value.data?.picture,
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                                val markwon = Markwon.builder(LocalContext.current)
                                    .usePlugin(HtmlPlugin.create())
                                    .build()
                                val text = markwon.toMarkdown(challenge.value.data?.description ?: "").toString()
                                Text(text = text)
                            }
                        }
                    }
                }

            }
        }
}


