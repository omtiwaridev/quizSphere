package com.op.domain.repository

import com.op.domain.model.QuizTopic
import com.op.domain.util.DataError
import com.op.domain.util.Result

interface QuizTopicRepository {
    suspend fun getAllTopics(): Result<List<QuizTopic>, DataError>
    suspend fun upsertTopic(topic: QuizTopic): Result<Unit, DataError>
    suspend fun getTopicById(id: String?): Result<QuizTopic, DataError>
    suspend fun deleteTopicById(id: String?): Result<Unit, DataError>
}