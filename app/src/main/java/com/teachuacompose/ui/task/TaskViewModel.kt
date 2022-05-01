package com.teachuacompose.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teachuacompose.data.model.dto.challenge.task.Task
import com.teachuacompose.data.model.dto.challenges.ChallengeItem
import com.teachuacompose.domain.challenges.ChallengesUseCasesInterface
import com.teachuacompose.domain.task.TaskUseCasesInterface
import com.teachuacompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskUseCases: TaskUseCasesInterface) : ViewModel(){

    private var _task = MutableStateFlow<Resource<Task>>(Resource.loading())

    val task : StateFlow<Resource<Task>>
        get() = _task


    fun load(id : Int) = viewModelScope.launch {
        _task.value = Resource.loading()
        _task.value = taskUseCases.getTask(id)
    }

}