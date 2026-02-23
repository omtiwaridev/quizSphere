package com.op.presentation.routes

import com.op.domain.model.QuizQuestion
import com.op.domain.repository.QuizQuestionRepository
import com.op.domain.util.onFailure
import com.op.domain.util.onSuccess
import com.op.presentation.routes.path.QuizQuestionRoutesPath
import com.op.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.resources.delete
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.Route

fun Route.quizQuestionsRoutes(repository: QuizQuestionRepository){
    post<QuizQuestionRoutesPath> {
        val question = call.receive<QuizQuestion>()
        repository.upsertQuizQuestion(question).onSuccess {
            call.respond(message = "Question Added Successfully", status = HttpStatusCode.Created)
        }.onFailure {  error ->
            respondWithError(error)
        }
    }

    get<QuizQuestionRoutesPath>{ path ->
        repository.getAllQuizQuestions(path.topicCode)
            .onSuccess { questions ->
                call.respond(message = questions, status = HttpStatusCode.OK)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
    get<QuizQuestionRoutesPath.ById> { path ->
        repository.getQuestionById(path.questionId)
            .onSuccess { question ->
                call.respond(
                    message = question,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
    get<QuizQuestionRoutesPath.Random>{ path ->
        repository.getRandomQuizQuestions(path.topicCode, path.limit)
            .onSuccess { questions ->
                call.respond(message = questions, status = HttpStatusCode.OK)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }

    post<QuizQuestionRoutesPath.Bulk> {
        val quizQuestion = call.receive<List<QuizQuestion>>()
        repository.insertQuestionsInBulk(quizQuestion)
            .onSuccess {
                call.respond(message = "Quiz questions added",
                    status = HttpStatusCode.Created)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
    delete<QuizQuestionRoutesPath.ById>{ path ->
        repository.deleteQuestionById(path.questionId)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}