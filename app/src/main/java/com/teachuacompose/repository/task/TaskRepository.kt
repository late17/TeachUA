package com.teachuacompose.repository.task

import com.teachuacompose.data.dataBase.dataSource.LocalDataSource
import com.teachuacompose.data.model.dto.challenge.task.Task
import com.teachuacompose.data.rest.dataSource.RemoteDataSource
import com.teachuacompose.util.Resource
import com.teachuacompose.util.performGetFromLocal
import com.teachuacompose.util.performGetFromRemote

class TaskRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val providesSharedPreferences: () -> Boolean
) : TaskRepositoryInterface
{

    override suspend fun getTask(id: Int): Resource<Task> {
        return if (providesSharedPreferences()){
            performGetFromRemote { remoteDataSource.getTask(id) }
        } else {
            performGetFromLocal(
                dbCall = {localDataSource.getTaskById(id)}
            ) { entity -> Task(entity!!)  }
        }
    }
}