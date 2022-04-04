package com.teachuacompose.dto

data class Task(
    val headerText: String,
    val id: Int,
    val name: String,
    val picture: String,
    val startDate: List<Int>
)