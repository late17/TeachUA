package com.teachuacompose.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.teachuacompose.data.dataBase.entity.ChallengeEntity
import com.teachuacompose.data.dataBase.entity.TaskEntity

@Database(entities = [ChallengeEntity::class, TaskEntity::class], version = 5)
abstract class TeachUaDatabase: RoomDatabase() {
    abstract fun challengeDao() : ChallengeDao

    abstract fun taskDao(): TaskDao
}