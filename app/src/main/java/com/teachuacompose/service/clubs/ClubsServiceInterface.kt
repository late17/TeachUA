package com.teachuacompose.service.clubs

import com.teachuacompose.dto.Clubs
import com.teachuacompose.util.Resource

interface ClubsServiceInterface {

    suspend fun getClubs() : Resource<Clubs>


}