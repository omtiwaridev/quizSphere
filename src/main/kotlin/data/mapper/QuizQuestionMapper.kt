package com.op.data.mapper

import com.op.data.database.entity.QuizQuestionEntity
import com.op.domain.model.QuizQuestion

fun QuizQuestionEntity.toQuizQuestion() = QuizQuestion(
    id = _id,
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers,
    explanations = explanations,
    topicCode = topicCode
)

fun QuizQuestion.toQuizQuestionEntity() = QuizQuestionEntity(
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers,
    explanations = explanations,
    topicCode = topicCode
)