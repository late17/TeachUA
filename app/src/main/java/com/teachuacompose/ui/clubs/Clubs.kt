package com.teachuacompose.ui.clubs

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import com.teachuacompose.ui.compose.util.TopBar

@Composable
fun Clubs(title: String, openDrawer: () -> Unit){
    Column() {
        TopBar(
            title = title,
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
    }
}