package com.teachuacompose.repository.task

import com.teachuacompose.data.model.dto.challenge.task.Task
import com.teachuacompose.util.Resource

interface TaskRepositoryInterface {

    suspend fun getTask(id: Int): Resource<Task>

}