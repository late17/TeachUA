package com.teachuacompose.dto.asdf

import com.google.gson.annotations.SerializedName

data class ClubsPage(
    @SerializedName("content")
    val clubs: List<Club>
)