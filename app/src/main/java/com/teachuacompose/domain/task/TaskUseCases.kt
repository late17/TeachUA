package com.teachuacompose.domain.task

import com.teachuacompose.data.model.dto.challenge.task.Task
import com.teachuacompose.repository.challenges.ChallengeRepositoryInterface
import com.teachuacompose.repository.task.TaskRepositoryInterface
import com.teachuacompose.util.Resource
import javax.inject.Inject

class TaskUseCases @Inject constructor(private val taskRepositoryInterface: TaskRepositoryInterface)  : TaskUseCasesInterface {
    override suspend fun getTask(id: Int): Resource<Task> {
        return taskRepositoryInterface.getTask(id)
    }
}