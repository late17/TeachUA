package com.teachuacompose.data.model.dto.challenge.task

import com.teachuacompose.data.dataBase.entity.TaskEntity

data class Task(
    val headerText: String,
    val id: Int,
    val name: String,
    val picture: String,
    val startDate: List<Int>,
    val description : String
){
    constructor(taskEntity: TaskEntity) : this(
        taskEntity.headerText,
        taskEntity.id,
        taskEntity.name,
        taskEntity.picture,
        emptyList(),
        taskEntity.description
    )
}