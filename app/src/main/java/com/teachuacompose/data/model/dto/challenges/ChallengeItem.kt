package com.teachuacompose.data.model.dto.challenges

import com.teachuacompose.data.dataBase.entity.ChallengeEntity

data class ChallengeItem(
    val id: Int,
    val name: String,
    val sortNumber: Int,
    val title: String
){
    constructor(challengeEntity: ChallengeEntity) :this(
        challengeEntity.id,
        challengeEntity.name,
        challengeEntity.sortNumber,
        challengeEntity.title
    )
}