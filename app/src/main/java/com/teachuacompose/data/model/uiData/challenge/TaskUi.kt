package com.teachuacompose.data.model.uiData.challenge

import com.teachuacompose.data.dataBase.entity.TaskEntity
import com.teachuacompose.data.model.dto.challenge.task.Task

data class TaskUi(
    val headerText: String,
    val id: Int,
    val name: String,
    val picture: String,
    val startDate: String
) {
    constructor(task : Task) : this(
        task.headerText,
        task.id,
        task.name,
        task.picture,
        task.startDate.toString()
    )

    constructor(task: TaskEntity) : this(
        task.headerText,
        task.id,
        task.name,
        task.picture,
        startDate = task.startDate
    )
}