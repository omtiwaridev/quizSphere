package com.op.presentation.config

import com.op.presentation.validator.validateIssueReport
import com.op.presentation.validator.validateQuizQuestion
import com.op.presentation.validator.validateQuizTopic
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation

fun Application.configureValidation(){
    install(RequestValidation) {
        validateQuizQuestion()
        validateQuizTopic()
        validateIssueReport()
    }
}