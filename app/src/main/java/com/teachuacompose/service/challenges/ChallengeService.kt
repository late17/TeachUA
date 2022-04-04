package com.teachuacompose.service.challenges

import androidx.lifecycle.LiveData
import com.teachuacompose.dto.Challenge
import com.teachuacompose.dto.Challenges
import com.teachuacompose.rest.remote.RemoteDataSource
import com.teachuacompose.util.Resource
import com.teachuacompose.util.performGetFromDB

class ChallengeService(private val remoteDataSource: RemoteDataSource) : ChallengeServiceInterface {

    override fun getChallengeById(id : Int): LiveData<Resource<Challenge>> =
        performGetFromDB (networkCall ={remoteDataSource.getChallengeById(id)}, key = id)

    override fun getChallenges(): LiveData<Resource<Challenges>> =
        performGetFromDB { remoteDataSource.getChallenges() }

}