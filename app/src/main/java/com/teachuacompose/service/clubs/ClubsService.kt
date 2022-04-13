package com.teachuacompose.service.clubs

import com.teachuacompose.data.model.dto.clubs.Clubs
import com.teachuacompose.data.rest.dataSource.RemoteDataSource
import com.teachuacompose.util.Resource
import com.teachuacompose.util.performGetFromRemote

class ClubsService (private val remoteDataSource: RemoteDataSource) : ClubsServiceInterface {


    override suspend fun getClubs(): Resource<Clubs> {
        return performGetFromRemote { remoteDataSource.getClubs() }
    }

}