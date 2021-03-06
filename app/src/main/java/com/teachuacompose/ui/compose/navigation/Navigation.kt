package com.teachuacompose.ui.compose.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.teachuacompose.ui.challenges.Challenge
import com.teachuacompose.ui.challenges.Challenges
import com.teachuacompose.ui.clubs.Clubs
import com.teachuacompose.ui.task.TaskComposable

sealed class Screen(val title : String,val route : String) {
    object Circles : Screen("Гуртки",  "circles")
    object Challenge : Screen("Челенджі",  "challenge")
    object AboutUs : Screen("Про нас",  "about_us")
    object Task : Screen("Завдання", "task")
    object ServiceInUkrainian : Screen("Послуги українською",  "service_in_ukrainian")

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
    Screen.AboutUs,
    Screen.ServiceInUkrainian
)


@Composable
fun Drawer(
    onDestinationClicked: (route: String) -> Unit,
    darkTheme: Boolean,
    changeTheme: () -> Unit,
    isLocalDatabase: Boolean,
    changeDatabase : () -> Unit
    ) {
    Surface(
        color = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(start = 5.dp)) {
            screens.forEach{ screen ->
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = screen.title,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .clickable {
                            onDestinationClicked(screen.route)
                        }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Divider(color = MaterialTheme.colors.onBackground, thickness = 1.dp)
            Spacer(modifier = Modifier.height(24.dp))
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp

            SettingRow("Dark theme",darkTheme, screenWidth) { changeTheme() }
            SettingRow("Local database", isLocalDatabase, screenWidth) { changeDatabase() }
        }
    }
}

@Composable
fun SettingRow(
    settingName: String,
    booleanToChange: Boolean,
    screenWidth: Dp,
    changeDatabase: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.width(screenWidth*2/3)){
            Text(settingName, style = MaterialTheme.typography.h5)
        }
        Switch(checked = booleanToChange, onCheckedChange = {changeDatabase()})
    }
}



@Composable
fun Navigation(navController: NavHostController, openDrawer: () -> Unit) {
    NavHost(navController = navController, startDestination = Screen.Challenge.route){


        composable(route = Screen.Circles.route){
            Clubs(Screen.Circles.title, openDrawer)
        }

        //CHALLENGES
        composable(route = Screen.Challenge.route){

            Challenges(
                Screen.Challenge.title,
                openDrawer
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
            Challenge(
                id,
                navigateToTask = { id -> navController.navigate(Screen.Task.route + "/$id")}
            ) { navController.popBackStack() }
        }

        composable(
            route = Screen.Task.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id") ?: 1
            TaskComposable(id) {navController.popBackStack()}
        }

        composable(
            route = Screen.AboutUs.route
        ) {}

        composable(
            route = Screen.ServiceInUkrainian.route
        ) {}
    }
}

