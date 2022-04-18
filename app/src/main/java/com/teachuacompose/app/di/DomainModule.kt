package com.teachuacompose.app.di

import com.teachuacompose.app.baseUrl
import com.teachuacompose.data.dataBase.TeachUaDatabase
import com.teachuacompose.data.dataBase.dataSource.LocalDataSource
import com.teachuacompose.data.rest.ApiClient
import com.teachuacompose.data.rest.dataSource.RemoteDataSource
import com.teachuacompose.service.challenges.ChallengeService
import com.teachuacompose.service.challenges.ChallengeInterface
import com.teachuacompose.service.clubs.ClubsService
import com.teachuacompose.service.clubs.ClubsInterface
import com.teachuacompose.service.mainActivity.MainActivityInterface
import com.teachuacompose.service.mainActivity.MainActivityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    //
    //*******SERVICES*******
    //
    @Singleton
    @Provides
    fun providesClubInterface() : ClubsInterface {
        return ClubsService(providesRemoteDataSource())
    }

    @Singleton
    @Provides
    fun providesChallengeInterface() : ChallengeInterface {
        return ChallengeService(providesRemoteDataSource(), providesLocalDataSource())
    }

    @Singleton
    @Provides
    fun providesMainActivityInterface() : MainActivityInterface {
        return MainActivityService()
    }

    //
    //*******LOCAL*******
    //
    @Provides
    fun providesTeachUaDatabase() : TeachUaDatabase.Companion{
        return TeachUaDatabase.Companion
    }

    @Singleton
    @Provides
    fun providesLocalDataSource() : LocalDataSource{
        return LocalDataSource(providesTeachUaDatabase())
    }


    //
    //*******REMOTE*******
    //
    @Singleton
    @Provides
    fun providesLoggingInterceptor() : HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(providesLoggingInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitRepository(): ApiClient {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient())
            .build().create(ApiClient::class.java)
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource() : RemoteDataSource {
        return RemoteDataSource(apiClient = providesRetrofitRepository())
    }
}