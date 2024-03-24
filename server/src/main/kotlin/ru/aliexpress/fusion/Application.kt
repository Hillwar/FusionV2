package ru.aliexpress.fusion

import URI
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        get("/molecule") {
            val fileName = URI

            val file = File(fileName)

            BufferedReader(FileReader(file)).use { reader ->
                val line = reader.readText()
                call.respondText(line)
            }
        }
    }
}