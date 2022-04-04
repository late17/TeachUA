package com.teachuacompose.dto

data class Challenge(
    val description: String,
    val id: Int,
    val isActive: Boolean,
    val name: String,
    val picture: String,
    val registrationLink: Any,
    val sortNumber: Int,
    val tasks: List<Task>,
    val title: String,
    val user: User
)
