package com.teachuacompose.data.rest.dataSource

import com.teachuacompose.data.rest.ApiClient
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiClient: ApiClient) : BaseDataSource() {

    suspend fun getCities() = getResult { apiClient.getCities()}

    suspend fun getClubs() = getResult { apiClient.getClubs() }

    suspend fun getChallengeById(id : Int) = getResult { apiClient.getChallengeById(id) }

    suspend fun getChallenges() = getResult { apiClient.getChallenges() }

}