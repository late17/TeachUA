package com.teachuacompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.teachuacompose.data.dataBase.TeachUaDatabase
import com.teachuacompose.ui.compose.navigation.Drawer
import com.teachuacompose.ui.compose.navigation.Navigation
import com.teachuacompose.ui.theme.TeachUaComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TeachUaDatabase.initTeachUaDatabase(context = applicationContext)

        setContent {
            AppMainScreen()
        }

    }
}

@Composable
fun AppMainScreen() {
    val mainActivityViewModel = hiltViewModel<MainActivityViewModel>()
    LaunchedEffect(key1 = true) {
        mainActivityViewModel.load()
    }
    val darkTheme by mainActivityViewModel.theme.collectAsState(false)

    TeachUaComposeTheme(darkTheme = darkTheme) {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()


            ModalDrawer(
                drawerState = drawerState,
                drawerContent = {
                    Drawer(
                        onDestinationClicked = { route ->
                            scope.launch { drawerState.close() }
                            navController.navigate(route)
                                               },
                        darkTheme
                    ) { mainActivityViewModel.switchDarkTheme(darkTheme) }
                }
            ) {
                Navigation(navController) {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }
        }
    }
}