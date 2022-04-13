package com.teachuacompose.service.challenges

import com.teachuacompose.data.dataBase.TeachUaDatabase
import com.teachuacompose.data.dataBase.entity.ChallengeEntity
import com.teachuacompose.data.model.dto.challenges.Challenges
import com.teachuacompose.data.model.uiData.challenge.ChallengeUi
import com.teachuacompose.data.rest.dataSource.RemoteDataSource
import com.teachuacompose.util.*

class ChallengeService(private val remoteDataSource: RemoteDataSource) : ChallengeServiceInterface {

    override suspend fun getChallenge(id: Int): Resource<ChallengeUi> {

        val result: Resource<ChallengeUi> = performGetFromDb(
            dbCall = {TeachUaDatabase.getTeachUaDatabase().challengeDao().getById(id)},
            entityToUi = { entity -> ChallengeUi(entity!!) },
        )
        return if (result.status == Resource.Status.SUCCESS){
            result
        } else {
            val remote = performGetFromRemote {
                remoteDataSource.getChallengeById(id)
            }
            if (remote.status == Resource.Status.SUCCESS){
                TeachUaDatabase.getTeachUaDatabase().challengeDao().addChallenge(ChallengeEntity(remote.data!!))
                Resource.success( ChallengeUi(remote.data) )
            } else {
                Resource.error()
            }
        }

    }

    override suspend fun getChallenges(): Resource<Challenges> {
        return performGetFromRemote {
            remoteDataSource.getChallenges()
        }
    }

}