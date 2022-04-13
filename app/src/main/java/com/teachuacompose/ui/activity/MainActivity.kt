package com.teachuacompose.ui.activity

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        TeachUaDatabase.buildTeachUaDatabase(context = applicationContext)
        setContent {
            AppMainScreen()
        }

    }
}

@Preview
@Composable
fun AppMainScreen() {
    TeachUaComposeTheme() {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()


            ModalDrawer(
                drawerState = drawerState,
                drawerContent = {
                    Drawer(onDestinationClicked = {
                            route ->
                        scope.launch { drawerState.close() }
                        navController.navigate(route)
                    }
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