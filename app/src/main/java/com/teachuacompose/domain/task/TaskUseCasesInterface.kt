package com.teachuacompose.domain.task

import com.teachuacompose.data.model.dto.challenge.task.Task
import com.teachuacompose.util.Resource

interface TaskUseCasesInterface {

    suspend fun getTask(id : Int) : Resource<Task>

}