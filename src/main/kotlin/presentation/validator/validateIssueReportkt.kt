package com.op.presentation.validator

import com.op.domain.model.IssueReport
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun RequestValidationConfig.validateIssueReport(){
    validate<IssueReport> { issueReport ->
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

        when {
         issueReport.questionId.isBlank() -> {
             ValidationResult.Invalid(reason = "Question ID must not be empty")
         }

         issueReport.issueType.isBlank() -> {
             ValidationResult.Invalid(reason = "Issue type must not be empty")
         }

         issueReport.timestamp.isBlank()  -> {
             ValidationResult.Invalid(reason = "Timestamp must not be empty")
         }

         issueReport.additionalComment != null && issueReport.additionalComment.length < 5 -> {
             ValidationResult.Invalid(reason = "Additional Comment must be at least 5 characters long")
         }

         issueReport.userEmail != null && !issueReport.userEmail.matches(emailRegex) -> {
             ValidationResult.Invalid(reason = "Invalid email format")
         }
         else -> {
             ValidationResult.Valid
         }
     }
    }
}