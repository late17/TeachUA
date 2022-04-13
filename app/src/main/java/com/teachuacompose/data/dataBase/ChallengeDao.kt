package com.teachuacompose.data.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teachuacompose.data.dataBase.entity.ChallengeEntity

@Dao
interface ChallengeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addChallenge(challenge : ChallengeEntity)

    @Query("Select * FROM challenge_table")
    suspend fun readAllData():List<ChallengeEntity>

    @Query("Select * From challenge_table where id = (:id)")
    suspend fun getById(id : Int):ChallengeEntity?

}