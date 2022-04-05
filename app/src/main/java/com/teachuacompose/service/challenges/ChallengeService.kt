package com.teachuacompose.service.challenges

import com.teachuacompose.dto.Challenge
import com.teachuacompose.dto.Challenges
import com.teachuacompose.rest.remote.RemoteDataSource
import com.teachuacompose.util.Resource
import com.teachuacompose.util.performGetFromRemote

class ChallengeService(private val remoteDataSource: RemoteDataSource) : ChallengeServiceInterface {

    override suspend fun getChallenge(id : Int): Resource<Challenge> {
        return performGetFromRemote {
            remoteDataSource.getChallengeById(id)
        }
    }
    override suspend fun getChallenges(): Resource<Challenges> {
        return performGetFromRemote {
            remoteDataSource.getChallenges()
        }
    }


}