package com.op.domain.repository

import com.op.domain.model.QuizQuestion
import com.op.domain.util.DataError
import com.op.domain.util.Result

interface QuizQuestionRepository {
    suspend fun upsertQuizQuestion(question: QuizQuestion): Result<Unit, DataError>
    suspend fun getAllQuizQuestions(topicCode: Int?): Result<List<QuizQuestion>, DataError>
    suspend fun getRandomQuizQuestions(topicCode: Int?, limit: Int?): Result<List<QuizQuestion>, DataError>
    suspend fun getQuestionById(id: String?): Result<QuizQuestion, DataError>
    suspend fun deleteQuestionById(id: String?): Result<Unit, DataError>
    suspend fun insertQuestionsInBulk(questions: List<QuizQuestion>): Result<Unit, DataError>
}