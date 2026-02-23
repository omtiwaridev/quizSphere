package com.op.presentation.util

import com.op.domain.util.DataError
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext

suspend fun RoutingContext.respondWithError(error: DataError){
    when(error) {
        DataError.Database -> {
           call.respond(message = "Database Error Occurred", status = HttpStatusCode.InternalServerError)
        }
        DataError.NotFound -> {
            call.respond(message = "Resource not found ", status = HttpStatusCode.NotFound)
        }
        DataError.Unknown -> {
            call.respond(message = "An Unknown Error Occurred", status = HttpStatusCode.InternalServerError)
        }
        DataError.Validation -> {
            call.respond(message = "Invalid Data Provided", status = HttpStatusCode.BadRequest)
        }
    }
}