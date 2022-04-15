package com.teachuacompose.data.dataBase.dataSource

import com.teachuacompose.data.dataBase.ChallengeDao
import com.teachuacompose.data.dataBase.TeachUaDatabase
import com.teachuacompose.data.dataBase.entity.ChallengeEntity
import javax.inject.Inject


class LocalDataSource @Inject constructor(private val teachUaDatabase : TeachUaDatabase.Companion) : ChallengeDao {

    override suspend fun addChallenge(challenge: ChallengeEntity) {
        teachUaDatabase.getTeachUaDatabase().challengeDao().addChallenge(challenge = challenge)
    }

    override suspend fun readAllData(): List<ChallengeEntity> {
        return teachUaDatabase.getTeachUaDatabase().challengeDao().readAllData()
    }

    override suspend fun getById(id: Int): ChallengeEntity? {
        return teachUaDatabase.getTeachUaDatabase().challengeDao().getById(id = id)
    }
}