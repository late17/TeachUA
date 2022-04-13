package com.teachuacompose.data.model.dto.challenge.task

data class Task(
    val headerText: String,
    val id: Int,
    val name: String,
    val picture: String,
    val startDate: List<Int>
)