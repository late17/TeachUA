package com.teachuacompose.ui.challenges

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.teachuacompose.dto.Challenge
import com.teachuacompose.service.challenges.ChallengeServiceInterface
import com.teachuacompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(private val challengesServiceInterface: ChallengeServiceInterface) : ViewModel() {

    var challenge : LiveData<Resource<Challenge>> = liveData {  }

    fun loadChallenge(id : Int){
        Log.e("tag", "message")
        challenge = challengesServiceInterface.getChallengeById(id)
    }

}