package com.teachuacompose.data.model.uiData.challenge

import com.teachuacompose.data.dataBase.entity.ChallengeEntity
import com.teachuacompose.data.dataBase.entity.TaskEntity
import com.teachuacompose.data.model.dto.challenge.ChallengeDto
import com.teachuacompose.data.model.dto.challenge.task.Task

data class ChallengeUi(
    val description: String,
    val id: Int,
    val name: String,
    val picture: String,
    val title: String,
    val tasks: List<TaskUi>
    ) {
    constructor(challengeEntity: ChallengeEntity, tasks: List<TaskEntity>) : this(
        challengeEntity.description,
        challengeEntity.id,
        challengeEntity.name,
        challengeEntity.picture,
        challengeEntity.title,
        tasks.map { it -> TaskUi(it) }
    )

    constructor(challengeDto: ChallengeDto) : this(
        challengeDto.description,
        challengeDto.id,
        challengeDto.name,
        challengeDto.picture,
        challengeDto.title,
        challengeDto.tasks.map { task -> TaskUi(task) }
    )
}
