package com.teachuacompose.data.model.uiData.challenge

import com.teachuacompose.data.dataBase.entity.ChallengeEntity
import com.teachuacompose.data.model.dto.challenge.ChallengeDto
import com.teachuacompose.data.model.dto.challenge.task.Task
import com.teachuacompose.util.parseHtml

data class ChallengeUi(
    val description: String,
    val id: Int,
    val name: String,
    val picture: String,
    val title: String,
) {
    constructor(challengeEntity: ChallengeEntity) : this(
        challengeEntity.description,
        challengeEntity.id,
        challengeEntity.name,
        challengeEntity.picture,
        challengeEntity.title
    )

    constructor(challengeDto: ChallengeDto) : this(
        challengeDto.description,
        challengeDto.id,
        challengeDto.name,
        challengeDto.picture,
        challengeDto.title,
    )
}
