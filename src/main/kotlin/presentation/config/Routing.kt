package com.op.presentation.config

import com.op.data.database.DatabaseFactory
import com.op.domain.repository.IssueReportRepository
import com.op.domain.repository.QuizQuestionRepository
import com.op.domain.repository.QuizTopicRepository
import com.op.presentation.root.root
import com.op.presentation.routes.issueReportRoutes
import com.op.presentation.routes.quizQuestionsRoutes
import com.op.presentation.routes.quizTopicRoutes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    install(Resources)
    val mongoDatabase = DatabaseFactory.create()
    val quizQuestionRepository: QuizQuestionRepository by inject()
    val quizTopicRepository: QuizTopicRepository by inject()
    val issueReportRepository: IssueReportRepository by inject()
    routing {
        root()
        // Quiz Question
         quizQuestionsRoutes(quizQuestionRepository)
        // Quiz Topic
        quizTopicRoutes(quizTopicRepository)
        // Issue Report
        issueReportRoutes(issueReportRepository)
        staticResources(
            remotePath = "/images",
            basePackage = "images"
        )
    }

}


