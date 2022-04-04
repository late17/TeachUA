package com.teachuacompose.service.clubs

import androidx.lifecycle.LiveData
import com.teachuacompose.dto.Clubs
import com.teachuacompose.util.Resource
import com.teachuacompose.rest.remote.RemoteDataSource
import com.teachuacompose.util.performGetFromDB

class ClubsService (private val remoteDataSource: RemoteDataSource) : ClubsServiceInterface {

    override fun getClubs(): LiveData<Resource<Clubs>> =
        performGetFromDB { remoteDataSource.getClubs() }

}