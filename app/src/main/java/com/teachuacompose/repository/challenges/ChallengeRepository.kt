package com.teachuacompose.repository.challenges

import com.teachuacompose.data.dataBase.dataSource.LocalDataSource
import com.teachuacompose.data.dataBase.entity.ChallengeEntity
import com.teachuacompose.data.model.dto.challenge.task.Task
import com.teachuacompose.data.model.dto.challenges.ChallengeItem
import com.teachuacompose.data.model.uiData.challenge.ChallengeUi
import com.teachuacompose.data.rest.dataSource.RemoteDataSource
import com.teachuacompose.util.*

class ChallengeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val providesSharedPreferences: () -> Boolean
) : ChallengeRepositoryInterface {

    private val boolean : Boolean
        get() = providesSharedPreferences()

    //in progress
    override suspend fun getChallenge(id: Int): Resource<ChallengeUi> {
        return if (boolean) {
            val challenge = localDataSource.getChallengeByIdWithTasks(id)
            if (challenge != null) {
                Resource.success(challenge)
            } else {
                Resource.error()
            }


        } else {
            val remote = performGetFromRemote {
                remoteDataSource.getChallengeById(id)
            }
            if (remote.status == Resource.Status.SUCCESS) {
                localDataSource.addChallenge(ChallengeEntity(remote.data!!))
                Resource.success(ChallengeUi(remote.data))
            } else {
                Resource.error()
            }
        }
    }

    override suspend fun getChallenges(): Resource<ArrayList<ChallengeItem>> {
        return if (boolean) {
            performGetFromLocal(
                dbCall = { localDataSource.getChallenges() }
            ) { entity ->
                entity.map { challengeEntity ->
                    ChallengeItem(
                        challengeEntity
                    )
                } as ArrayList
            }
        } else {
            performGetFromRemote { remoteDataSource.getChallenges() }
        }
    }

}