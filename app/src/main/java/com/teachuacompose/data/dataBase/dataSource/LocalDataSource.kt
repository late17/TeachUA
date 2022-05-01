package com.teachuacompose.data.dataBase.dataSource

import com.teachuacompose.data.dataBase.ChallengeDao
import com.teachuacompose.data.dataBase.TaskDao
import com.teachuacompose.data.dataBase.TeachUaDatabase
import com.teachuacompose.data.dataBase.entity.ChallengeEntity
import com.teachuacompose.data.dataBase.entity.TaskEntity
import com.teachuacompose.data.model.uiData.challenge.ChallengeUi
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val roomDatabase: TeachUaDatabase
    ) :
    ChallengeDao,
    TaskDao
{

    override suspend fun addChallenge(challenge: ChallengeEntity) {
        roomDatabase.challengeDao().addChallenge(challenge = challenge)
    }

    override suspend fun getChallengesById(id: Int): ChallengeEntity? {
        return roomDatabase.challengeDao().getChallengesById(id = id)
    }

    suspend fun getChallengeByIdWithTasks(id:Int): ChallengeUi?{
        val challenge =  roomDatabase.challengeDao().getChallengesById(id)
        val tasks = roomDatabase.taskDao().getTaskByChallengeId(id)
        return challenge?.let { ChallengeUi(it, tasks?: emptyList()) }
    }

    override suspend fun getChallenges(): List<ChallengeEntity> {
        return roomDatabase.challengeDao().getChallenges()
    }

    override suspend fun addTask(task: TaskEntity) {
        return roomDatabase.taskDao().addTask(task)
    }

    override suspend fun getTasks(): List<TaskEntity> {
        return roomDatabase.taskDao().getTasks()
    }

    override suspend fun getTaskById(id: Int): TaskEntity? {
        return roomDatabase.taskDao().getTaskById(id)
    }

    override suspend fun getTaskByChallengeId(id: Int): List<TaskEntity>? {
        return roomDatabase.taskDao().getTaskByChallengeId(id)
    }
}