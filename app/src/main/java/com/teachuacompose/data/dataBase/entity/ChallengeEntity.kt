package com.teachuacompose.data.dataBase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teachuacompose.data.model.dto.challenge.ChallengeDto

@Entity(tableName = "challenge_table")
data class ChallengeEntity(

    val description: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val picture: String,
    val title: String,
) {
    constructor(challengeDto: ChallengeDto) : this(
        challengeDto.description,
        challengeDto.id,
        challengeDto.name,
        challengeDto.picture,
        challengeDto.title
    )
}
