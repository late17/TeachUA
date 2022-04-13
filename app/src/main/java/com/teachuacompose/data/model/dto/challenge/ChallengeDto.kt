package com.teachuacompose.data.model.dto.challenge

import com.teachuacompose.data.model.dto.challenge.task.Task
import com.teachuacompose.data.model.dto.challenge.task.User

data class ChallengeDto(
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
