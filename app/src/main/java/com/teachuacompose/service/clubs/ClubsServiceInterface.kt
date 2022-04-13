package com.teachuacompose.service.clubs

import com.teachuacompose.data.model.dto.clubs.Clubs
import com.teachuacompose.util.Resource

interface ClubsServiceInterface {

    suspend fun getClubs() : Resource<Clubs>


}