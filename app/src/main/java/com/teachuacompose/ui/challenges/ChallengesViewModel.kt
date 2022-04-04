package com.teachuacompose.ui.challenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.teachuacompose.dto.Challenges
import com.teachuacompose.service.challenges.ChallengeServiceInterface
import com.teachuacompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChallengesViewModel @Inject constructor(private val challengesServiceInterface: ChallengeServiceInterface) : ViewModel() {

    var challenges : LiveData<Resource<Challenges>> = liveData { Resource.loading(null) }

    fun loadChallenges() {
        challenges = challengesServiceInterface.getChallenges()
    }

}