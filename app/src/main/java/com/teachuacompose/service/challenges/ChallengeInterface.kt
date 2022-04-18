package com.teachuacompose.service.challenges

import com.teachuacompose.data.model.dto.challenges.Challenges
import com.teachuacompose.data.model.uiData.challenge.ChallengeUi
import com.teachuacompose.util.Resource

interface ChallengeInterface {

    suspend fun getChallenges() : Resource<Challenges>
    suspend fun getChallenge(id: Int):  Resource<ChallengeUi>

}