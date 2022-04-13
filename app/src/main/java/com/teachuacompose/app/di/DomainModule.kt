package com.teachuacompose.app.di

import com.teachuacompose.app.baseUrl
import com.teachuacompose.data.rest.ApiClient
import com.teachuacompose.data.rest.dataSource.RemoteDataSource
import com.teachuacompose.service.challenges.ChallengeService
import com.teachuacompose.service.challenges.ChallengeServiceInterface
import com.teachuacompose.service.clubs.ClubsService
import com.teachuacompose.service.clubs.ClubsServiceInterface
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

    @Singleton
    @Provides
    fun providesClubServiceInterface() : ClubsServiceInterface {
        return ClubsService(providesRemoteDataSource())
    }

    @Singleton
    @Provides
    fun providesChallengeServiceInterface() : ChallengeServiceInterface {
        return ChallengeService(providesRemoteDataSource())
    }
}