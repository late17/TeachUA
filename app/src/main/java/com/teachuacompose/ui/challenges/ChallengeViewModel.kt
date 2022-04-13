package com.teachuacompose.ui.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teachuacompose.data.model.uiData.challenge.ChallengeUi
import com.teachuacompose.service.challenges.ChallengeServiceInterface
import com.teachuacompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(private val challengesServiceInterface: ChallengeServiceInterface) : ViewModel() {

    private var _challenge = MutableStateFlow<Resource<ChallengeUi>>(Resource.loading())

    val challenge : StateFlow<Resource<ChallengeUi>>
        get() = _challenge


    fun load(id : Int) = viewModelScope.launch {
        _challenge.value = Resource.loading()
        _challenge.value = challengesServiceInterface.getChallenge(id)
    }

}