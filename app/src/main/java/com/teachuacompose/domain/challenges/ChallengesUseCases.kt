package com.teachuacompose.domain.challenges

import android.util.Log
import com.teachuacompose.data.model.dto.challenges.ChallengeItem
import com.teachuacompose.data.model.dto.challenges.Challenges
import com.teachuacompose.data.model.uiData.challenge.ChallengeUi
import com.teachuacompose.repository.challenges.ChallengeRepositoryInterface
import com.teachuacompose.util.Resource
import javax.inject.Inject

class ChallengesUseCases @Inject constructor(private val challengeService : ChallengeRepositoryInterface) : ChallengesUseCasesInterface{

    override suspend fun getChallenges(): Resource<ArrayList<ChallengeItem>> {
        val challenges = challengeService.getChallenges()
        challenges.data?.sortByDescending { it.sortNumber }
        return challenges
    }

    override suspend fun getChallenge(id : Int) : Resource<ChallengeUi> {

        return challengeService.getChallenge(id = id)
    }

}