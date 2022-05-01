package com.teachuacompose.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.teachuacompose.app.MyService
import com.teachuacompose.ui.compose.navigation.Drawer
import com.teachuacompose.ui.compose.navigation.Navigation
import com.teachuacompose.ui.theme.TeachUaComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MyService::class.java)
        startService(intent)
        setContent {
            AppMainScreen()
        }
    }
}

@Composable
fun AppMainScreen() {
    val viewModel = hiltViewModel<MainActivityViewModel>()
    LaunchedEffect(key1 = true) {
        viewModel.eventHandler(MainActivityEvents.LOAD_DATA)
    }
    val darkTheme by viewModel.theme.collectAsState(false)
    val isLocalDatabase by viewModel.isLocalDatabase.collectAsState(false)

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
                        darkTheme,
                        { viewModel.eventHandler(MainActivityEvents.THEME_BUTTON) },
                        isLocalDatabase,
                        { viewModel.eventHandler(MainActivityEvents.DATABASE_BUTTON)}
                    )
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