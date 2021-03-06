package com.teachuacompose.data.rest


import com.teachuacompose.data.model.dto.challenge.ChallengeDto
import com.teachuacompose.data.model.dto.challenge.task.Task
import com.teachuacompose.data.model.dto.challenges.ChallengeItem
import com.teachuacompose.data.model.dto.challenges.Challenges
import com.teachuacompose.data.model.dto.cities.Cities
import com.teachuacompose.data.model.dto.clubs.Clubs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiClient {

    @GET("challenge/{id}")
    suspend fun getChallengeById(@Path("id") id : Int): Response<ChallengeDto>

    @GET("challenges")
    suspend fun getChallenges(): Response<ArrayList<ChallengeItem>>

    @GET("cities")
    suspend fun getCities():Response<Cities>

    @GET("clubs")
    suspend fun getClubs():Response<Clubs>

    @GET("challenge/task/{id}")
    suspend fun getTask(@Path("id") id : Int):Response<Task>
}
