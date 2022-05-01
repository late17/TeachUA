package com.teachuacompose.app.di

import android.content.Context
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import androidx.room.Room
import com.teachuacompose.app.baseUrl
import com.teachuacompose.data.dataBase.TeachUaDatabase
import com.teachuacompose.data.dataBase.dataSource.LocalDataSource
import com.teachuacompose.data.rest.ApiClient
import com.teachuacompose.data.rest.dataSource.RemoteDataSource
import com.teachuacompose.domain.challenges.ChallengesUseCases
import com.teachuacompose.domain.challenges.ChallengesUseCasesInterface
import com.teachuacompose.domain.mainActivity.MainActivityUseCases
import com.teachuacompose.domain.mainActivity.MainActivityUseCasesInterface
import com.teachuacompose.domain.task.TaskUseCases
import com.teachuacompose.domain.task.TaskUseCasesInterface
import com.teachuacompose.repository.challenges.ChallengeRepository
import com.teachuacompose.repository.challenges.ChallengeRepositoryInterface
import com.teachuacompose.repository.clubs.ClubsService
import com.teachuacompose.repository.clubs.ClubsInterface
import com.teachuacompose.repository.mainActivity.MainActivityRepository
import com.teachuacompose.repository.mainActivity.MainActivityRepositoryInterface
import com.teachuacompose.repository.task.TaskRepository
import com.teachuacompose.repository.task.TaskRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule () {

    //
    //********useCases********
    //
    @Singleton
    @Provides
    fun providesChallengeUseCases(@ApplicationContext appContext: Context) : ChallengesUseCasesInterface{
        return ChallengesUseCases(providesChallengeRepository(appContext))
    }

    @Singleton
    @Provides
    fun mainActivityUseCases(@ApplicationContext appContext: Context) : MainActivityUseCasesInterface {
        return MainActivityUseCases(providesMainActivityRepository(appContext))
    }

    @Singleton
    @Provides
    fun taskUseCases(@ApplicationContext appContext: Context) : TaskUseCasesInterface {
        return TaskUseCases(providesTaskRepository(appContext))
    }
    //
    //*******Repository*******
    //
    @Singleton
    @Provides
    fun providesClubRepository() : ClubsInterface {
        return ClubsService(providesRemoteDataSource())
    }

    @Singleton
    @Provides
    fun providesChallengeRepository(@ApplicationContext appContext: Context) : ChallengeRepositoryInterface {
        return ChallengeRepository(
            providesRemoteDataSource(),
            providesLocalDataSource(appContext)
        ) { providesSharedPreferences(appContext).getBoolean("isLocalDatabase", false) }
    }

    @Singleton
    @Provides
    fun providesMainActivityRepository(@ApplicationContext appContext: Context) : MainActivityRepositoryInterface {
        return MainActivityRepository(providesSharedPreferences(appContext))
    }

    @Singleton
    @Provides
    fun providesTaskRepository(@ApplicationContext appContext: Context) : TaskRepositoryInterface{
        return TaskRepository(
            providesRemoteDataSource(),
            providesLocalDataSource(appContext)
        ) { providesSharedPreferences(appContext).getBoolean("isLocalDatabase", false) }
    }
    //
    //*******LOCAL*******
    //
    @Singleton
    @Provides
    fun providesRoomDataBase(@ApplicationContext appContext: Context): TeachUaDatabase {
        return Room.databaseBuilder(
            appContext,
            TeachUaDatabase::class.java,
            "teach_ua_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesLocalDataSource(@ApplicationContext appContext: Context):LocalDataSource{
        return LocalDataSource(providesRoomDataBase(appContext))
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

    //
    //*******Shared Pref*******
    //
    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences(
            "Settings",
            ComponentActivity.MODE_PRIVATE
        )
    }
}