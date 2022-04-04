package com.teachuacompose.ui.compose

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.teachuacompose.ui.challenges.ChallengesViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.teachuacompose.ui.challenges.Challenge
import com.teachuacompose.ui.challenges.ChallengeViewModel
import com.teachuacompose.ui.challenges.Challenges
import com.teachuacompose.util.Resource

sealed class Screen(val title : String,val route : String) {
    object Circles : Screen("Гуртки",  "circles")
    object Challenge : Screen("Челенджі",  "challenge")

    fun withArgs( vararg args : String ):String {
        return buildString {
            append(route)
            args.forEach { append("/$it") }
        }
    }
}

private val screens = listOf(
    Screen.Circles,
    Screen.Challenge,
)

@Composable
fun Drawer(
    onDestinationClicked :(route :String) -> Unit
) {
    Surface(
        color = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            screens.forEach{ screen ->
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = screen.title,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.clickable{
                        onDestinationClicked(screen.route)
                    }
                )
            }
        }
    }
}

@Composable
fun TopBar(title : String , buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onButtonClicked()
            } ) {
                Icon(buttonIcon, contentDescription = "")
            }
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Composable
fun Navigation(navController: NavHostController, openDrawer: () -> Unit) {
    NavHost(navController = navController, startDestination = Screen.Challenge.route){


        composable(route = Screen.Circles.route){
            Circle(Screen.Circles.title, openDrawer)
        }

        //CHALLENGES
        composable(route = Screen.Challenge.route){
            val viewModel =  hiltViewModel<ChallengesViewModel>()
            LaunchedEffect(key1 = true ) {
                viewModel.loadChallenges()
            }
            val challenges = viewModel.challenges.observeAsState(Resource.loading())
            Challenges(
                Screen.Challenge.title,
                openDrawer,
                challenges
            ) { id -> navController.navigate(Screen.Challenge.withArgs(id.toString())) }
        }

        //CHALLENGE
        composable(
            route = Screen.Challenge.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ){ entry ->
            val id = entry.arguments?.getInt("id") ?: 1
            val challengeViewModel = hiltViewModel<ChallengeViewModel>()
            LaunchedEffect(key1 = true ) {
                challengeViewModel.loadChallenge(id)
            }
            val challenge = challengeViewModel.challenge.observeAsState(Resource.loading())


            Challenge(challenge,
               viewModel = challengeViewModel

            ) { navController.popBackStack() }
        }
    }
}

@Composable
fun Circle(title: String, openDrawer: () -> Unit){
    Column() {
        TopBar(
            title = title,
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
    }
}