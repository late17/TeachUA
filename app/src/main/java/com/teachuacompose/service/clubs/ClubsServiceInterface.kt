package com.teachuacompose.service.clubs

import androidx.lifecycle.LiveData
import com.teachuacompose.dto.Clubs
import com.teachuacompose.util.Resource

interface ClubsServiceInterface {

    fun getClubs() : LiveData<Resource<Clubs>>

}