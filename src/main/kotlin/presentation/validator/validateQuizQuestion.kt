package com.op.presentation.validator

import com.op.domain.model.QuizQuestion
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun RequestValidationConfig.validateQuizQuestion(){
    validate<QuizQuestion> { quizQuestion ->
     when {
         quizQuestion.question.isBlank() || quizQuestion.question.length < 5 -> {
             ValidationResult.Invalid(reason = "Question must be at least 5 characters long and must not be empty")
         }

         quizQuestion.correctAnswer.isBlank() -> {
             ValidationResult.Invalid(reason = "Correct answer must not be empty")
         }

         quizQuestion.incorrectAnswers.any { it.isBlank() } -> {
             ValidationResult.Invalid(reason = "Incorrect answers must not be empty")
         }
         quizQuestion.incorrectAnswers.isEmpty()  -> {
             ValidationResult.Invalid(reason = "There must be at least one incorrect answer")
         }

         quizQuestion.explanations.isBlank() -> {
             ValidationResult.Invalid(reason = "Explanation must not be empty")
         }

         quizQuestion.topicCode <= 0 -> {
             ValidationResult.Invalid(reason = "TopicCode must be a positive integer")
         }
         else -> {
             ValidationResult.Valid
         }
     }
    }
}