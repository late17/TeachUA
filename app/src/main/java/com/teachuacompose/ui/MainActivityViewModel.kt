package com.teachuacompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teachuacompose.service.mainActivity.MainActivityInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainActivityInterface: MainActivityInterface) : ViewModel() {

    private var _darkTheme = MutableStateFlow<Boolean>(false)

    val theme : StateFlow<Boolean>
        get() = _darkTheme


    fun load() = viewModelScope.launch {
        _darkTheme.value = false
        _darkTheme.value = mainActivityInterface.getDarkTheme()
    }

    fun switchDarkTheme(state : Boolean){
        _darkTheme.value = !state
    }

}