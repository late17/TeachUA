package com.teachuacompose.data.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teachuacompose.data.dataBase.entity.TaskEntity

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task : TaskEntity)

    @Query("Select * FROM task_table")
    suspend fun getTasks():List<TaskEntity>

    @Query("Select * From task_table where id = (:id)")
    suspend fun getTaskById(id : Int):TaskEntity?

    @Query("Select * From task_table where challengeId = (:id)")
    suspend fun getTaskByChallengeId(id : Int):List<TaskEntity>?
}