package com.op.presentation.routes.path

import io.ktor.resources.Resource

@Resource(path = "/quiz/topics")
class QuizTopicRoutesPath {

    @Resource(path = "/{topicId}")
    data class ById(
        val parent: QuizTopicRoutesPath = QuizTopicRoutesPath(),
                    val topicId: String
    )
}