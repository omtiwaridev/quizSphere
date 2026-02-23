package com.op.presentation.config

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configureStatusPages(){
    install(StatusPages){
        exception<RequestValidationException> { call, cause ->
         call.respond(message = cause.reasons.joinToString(),
             status = HttpStatusCode.BadRequest)
        }
    }
}