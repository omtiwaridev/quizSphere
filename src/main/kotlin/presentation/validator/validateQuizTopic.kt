package com.op.presentation.validator

import com.op.domain.model.QuizQuestion
import com.op.domain.model.QuizTopic
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun RequestValidationConfig.validateQuizTopic(){
    validate<QuizTopic> { quizTopic ->
     when {
         quizTopic.name.isBlank() || quizTopic.name.length < 3 -> {
             ValidationResult.Invalid(reason = "Question must be at least 5 characters long and must not be empty")
         }

         quizTopic.imageUrl.isBlank() -> {
             ValidationResult.Invalid(reason = "Image url must not be empty")
         }
         quizTopic.code < 0 -> {
             ValidationResult.Invalid(reason = "TopicCode must be a whole number")
         }
         else -> {
             ValidationResult.Valid
         }
     }
    }
}