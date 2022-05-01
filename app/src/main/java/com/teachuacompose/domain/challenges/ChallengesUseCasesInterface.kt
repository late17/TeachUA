package com.teachuacompose.domain.challenges

import com.teachuacompose.data.model.dto.challenges.ChallengeItem
import com.teachuacompose.data.model.dto.challenges.Challenges
import com.teachuacompose.data.model.uiData.challenge.ChallengeUi
import com.teachuacompose.util.Resource

interface ChallengesUseCasesInterface {

    suspend fun getChallenges(): Resource<ArrayList<ChallengeItem>>

    suspend fun getChallenge(id: Int): Resource<ChallengeUi>
}