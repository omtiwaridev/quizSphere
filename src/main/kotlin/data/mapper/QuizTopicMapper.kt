package com.op.data.mapper

import com.op.data.database.entity.QuizTopicEntity
import com.op.domain.model.QuizTopic

fun QuizTopicEntity.toQuizTopic() = QuizTopic(
    id = _id,
    name = name,
    imageUrl = imageUrl,
    code = code
)

fun QuizTopic.toQuizTopicEntity() = QuizTopicEntity(
    name = name,
    imageUrl = imageUrl,
    code = code
)