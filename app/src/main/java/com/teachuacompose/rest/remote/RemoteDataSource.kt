package com.teachuacompose.rest.remote

import com.teachuacompose.rest.ApiClient
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val apiClient: ApiClient) : BaseDataSource() {

    suspend fun getCities() = getResult { apiClient.getCities()}

    suspend fun getClubs() = getResult { apiClient.getClubs() }

    suspend fun getChallengeById(id : Int) = getResult { apiClient.getChallengeById(id) }

    suspend fun getChallenges() = getResult { apiClient.getChallenges() }

}