package com.teachuacompose.data.dataBase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teachuacompose.data.model.dto.challenge.task.Task

@Entity(tableName = "task_table")
data class TaskEntity(
    val headerText: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val picture: String,
    val startDate: String,
    @ColumnInfo(name = "description", defaultValue = "")
    val description: String,
    val challengeId : Int,
    ) {
    constructor(task: Task, id: Int) : this(
        task.headerText,
        task.id,
        task.name,
        task.picture,
        task.startDate.toString(),
        task.description ?: "",
        id
    )
}
