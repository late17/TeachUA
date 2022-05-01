package com.teachuacompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teachuacompose.domain.mainActivity.MainActivityUseCasesInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val useCases: MainActivityUseCasesInterface) : ViewModel() {

    private var _isLocalDatabase = MutableStateFlow<Boolean>(false)

    val isLocalDatabase : StateFlow<Boolean>
        get() = _isLocalDatabase

    private var _darkTheme = MutableStateFlow<Boolean>(false)

    val theme : StateFlow<Boolean>
        get() = _darkTheme

    private fun load() = viewModelScope.launch {
        _darkTheme.value = useCases.getDarkTheme()
        _isLocalDatabase.value = useCases.getIsLocalDatabase()
    }

    //not the best pattern but for now OK
    //Those are function that handle events on UI
    private fun switchDarkTheme(){
        useCases.changeDarkTheme(!_darkTheme.value)
        _darkTheme.value = !_darkTheme.value
    }

    private fun switchLocalDatabase() {
        useCases.changeIsLocalDatabase(!_isLocalDatabase.value)
        _isLocalDatabase.value = !_isLocalDatabase.value
    }

    fun eventHandler(event : MainActivityEvents){
        when(event){
            MainActivityEvents.DATABASE_BUTTON -> switchLocalDatabase()
            MainActivityEvents.THEME_BUTTON -> switchDarkTheme()
            MainActivityEvents.LOAD_DATA -> load()
        }
    }
}

enum class MainActivityEvents{
    LOAD_DATA,
    DATABASE_BUTTON,
    THEME_BUTTON
}