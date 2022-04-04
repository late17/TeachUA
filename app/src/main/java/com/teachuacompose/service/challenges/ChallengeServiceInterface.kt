package com.teachuacompose.service.challenges

import androidx.lifecycle.LiveData
import com.teachuacompose.dto.Challenge
import com.teachuacompose.dto.Challenges
import com.teachuacompose.util.Resource

interface ChallengeServiceInterface {

    fun getChallengeById(id : Int) : LiveData<Resource<Challenge>>

    fun getChallenges() : LiveData<Resource<Challenges>>


}