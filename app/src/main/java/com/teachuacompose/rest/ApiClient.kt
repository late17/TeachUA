package com.teachuacompose.rest

import com.teachuacompose.dto.Challenge
import com.teachuacompose.dto.Challenges
import com.teachuacompose.dto.Cities
import com.teachuacompose.dto.Clubs
import com.teachuacompose.dto.asdf.ClubsPage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiClient {

    @GET("challenge/{id}")
    suspend fun getChallengeById(@Path("id") id : Int): Response<Challenge>

    @GET("challenges")
    suspend fun getChallenges(): Response<Challenges>

    @GET("cities")
    suspend fun getCities():Response<Cities>

    @GET("clubs")
    suspend fun getClubs():Response<Clubs>

    @GET("clubs/search?clubName=&cityName=%D0%9A%D0%B8%D1%97%D0%B2&isOnline=false&categoryName=&page=0")
    suspend fun getClubsAdvanced():ClubsPage
}
