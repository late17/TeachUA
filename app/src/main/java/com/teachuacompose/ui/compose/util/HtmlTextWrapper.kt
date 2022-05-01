package com.teachuacompose.ui.compose.util

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teachuacompose.data.model.uiData.challenge.ChallengeUi
import com.teachuacompose.ui.compose.HtmlText
import com.teachuacompose.util.Resource

@Composable
fun HtmlTextWrapper(string: String) {
    HtmlText(text = string, modifier = Modifier.padding(4.dp))
}