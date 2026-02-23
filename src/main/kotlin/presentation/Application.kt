package com.op.presentation

import com.op.presentation.config.configureKoin
import com.op.presentation.config.configureLogging
import com.op.presentation.config.configureRouting
import com.op.presentation.config.configureSerialization
import com.op.presentation.config.configureStatusPages
import com.op.presentation.config.configureValidation
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureKoin()
    configureStatusPages()
    configureValidation()
    configureLogging()
    configureSerialization()
    configureRouting()
}
