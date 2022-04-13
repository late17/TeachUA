package com.teachuacompose.data.model.uiData.challenge

import androidx.compose.ui.text.AnnotatedString
import com.teachuacompose.data.dataBase.entity.ChallengeEntity
import com.teachuacompose.data.model.dto.challenge.ChallengeDto
import com.teachuacompose.util.parseHtml

data class ChallengeUi(
    val description: AnnotatedString,
    val id: Int,
    val name: String,
    val picture: String,
    val title: String,
) {
    constructor(challengeEntity: ChallengeEntity) : this(
        challengeEntity.description.parseHtml(),
        challengeEntity.id,
        challengeEntity.name,
        challengeEntity.picture,
        challengeEntity.title
    )

    constructor(challengeDto: ChallengeDto) : this(
        challengeDto.description.parseHtml(),
        challengeDto.id,
        challengeDto.name,
        challengeDto.picture,
        challengeDto.title
    )
}
