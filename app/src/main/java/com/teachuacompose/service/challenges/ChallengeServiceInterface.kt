package com.teachuacompose.service.challenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.teachuacompose.dto.Challenge
import com.teachuacompose.dto.Challenges
import com.teachuacompose.util.Resource

interface ChallengeServiceInterface {

    suspend fun getChallenges() : Resource<Challenges>
    suspend fun getChallenge(id: Int): Resource<Challenge>
}