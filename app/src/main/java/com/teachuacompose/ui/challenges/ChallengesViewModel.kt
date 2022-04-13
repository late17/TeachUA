package com.teachuacompose.ui.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teachuacompose.data.model.dto.challenges.Challenges
import com.teachuacompose.service.challenges.ChallengeServiceInterface
import com.teachuacompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengesViewModel @Inject constructor(private val challengesServiceInterface: ChallengeServiceInterface) : ViewModel() {

    private var _challenges = MutableStateFlow<Resource<Challenges>>(Resource.loading())

    val challenges : StateFlow<Resource<Challenges>>
        get() = _challenges


    fun load() = viewModelScope.launch {
        _challenges.value = Resource.loading()
        _challenges.value = challengesServiceInterface.getChallenges()
    }

}