package com.teachuacompose.app

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.room.RoomDatabase
import com.teachuacompose.data.dataBase.TeachUaDatabase
import com.teachuacompose.data.dataBase.dataSource.LocalDataSource
import com.teachuacompose.data.dataBase.entity.ChallengeEntity
import com.teachuacompose.data.dataBase.entity.TaskEntity
import com.teachuacompose.data.model.dto.challenges.ChallengeItem
import com.teachuacompose.data.rest.dataSource.RemoteDataSource
import com.teachuacompose.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyService : Service() {

    @Inject
    lateinit var localDataSource: LocalDataSource

    @Inject
    lateinit var remoteDataSource: RemoteDataSource

    @Inject
    lateinit var room: TeachUaDatabase

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(Dispatchers.IO).launch {
            val challenges = remoteDataSource.getChallenges()
            loadChallengesToLocalDatabase(challenges)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }


    private suspend fun loadChallengesToLocalDatabase(challenges: Resource<ArrayList<ChallengeItem>>) {
        if (challenges.status == Resource.Status.SUCCESS) {
            challenges.data?.forEach { challenge ->
                val challengeById = remoteDataSource.getChallengeById(challenge.id)
                if (challengeById.status == Resource.Status.SUCCESS) {
                    challengeById.data?.tasks?.let {
                        it.forEach { task ->
                            val fullTaskWithDescription = remoteDataSource.getTask(task.id)
                            if (fullTaskWithDescription.status == Resource.Status.SUCCESS) {
                                localDataSource.addTask(
                                    TaskEntity(
                                        fullTaskWithDescription.data!!,
                                        challenge.id
                                    )
                                )
                            }
                        }
                    }
                    val challengeEntity = challengeById.data?.let { ChallengeEntity(it) }
                    challengeEntity?.let { localDataSource.addChallenge(it) }
                }
            }
        }
    }
}