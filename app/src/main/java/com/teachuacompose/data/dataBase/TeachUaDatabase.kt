package com.teachuacompose.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.teachuacompose.data.dataBase.entity.ChallengeEntity
import kotlin.properties.Delegates.notNull

@Database(entities = [ChallengeEntity::class], version = 1, exportSchema = false)
abstract class TeachUaDatabase: RoomDatabase() {

    abstract fun challengeDao() : ChallengeDao

    companion object{
        private var instance : TeachUaDatabase by notNull()

        fun getTeachUaDatabase() : TeachUaDatabase{
            return instance
        }

        fun buildTeachUaDatabase(context: Context) {
            instance = Room.databaseBuilder(
                    context,
                    TeachUaDatabase::class.java,
                    "teach_ua_database"
                ).build()
        }
    }
}