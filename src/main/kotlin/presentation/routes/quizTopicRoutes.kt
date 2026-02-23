package com.op.presentation.routes

import com.op.domain.model.QuizTopic
import com.op.domain.repository.QuizTopicRepository
import com.op.domain.util.onFailure
import com.op.domain.util.onSuccess
import com.op.presentation.routes.path.QuizTopicRoutesPath
import com.op.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.resources.delete
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.Route

fun Route.quizTopicRoutes(repository: QuizTopicRepository){
    post<QuizTopicRoutesPath> {
        val quizTopic = call.receive<QuizTopic>()
        repository.upsertTopic(quizTopic)
            .onSuccess {
                call.respond(message = "Quiz topic added", status = HttpStatusCode.Created)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
    get<QuizTopicRoutesPath> {
        repository.getAllTopics()
            .onSuccess { topics ->
                call.respond(message = topics, status = HttpStatusCode.OK)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }

    get<QuizTopicRoutesPath.ById> { path ->
        repository.getTopicById(path.topicId)
            .onSuccess { quizTopic ->
                call.respond(message = quizTopic,
                    status = HttpStatusCode.OK)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
    delete<QuizTopicRoutesPath.ById> { path ->
        repository.deleteTopicById(path.topicId)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }
            .onFailure {  error ->
                respondWithError(error)
            }
    }
}