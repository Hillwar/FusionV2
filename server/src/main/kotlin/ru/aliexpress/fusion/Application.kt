package ru.aliexpress.fusion

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
        get("/molecule/shipping") {
            val fileName = System.getProperty("user.dir") + "/server/src/main/kotlin/ru/aliexpress/fusion/molecules/shipping.json"

            val file = File(fileName)

            BufferedReader(FileReader(file)).use { reader ->
                val line = reader.readText()
                call.respondText(line)
            }
        }

        get("/molecule/title") {
            val fileName = System.getProperty("user.dir") + "/server/src/main/kotlin/ru/aliexpress/fusion/molecules/title.json"

            val file = File(fileName)

            BufferedReader(FileReader(file)).use { reader ->
                val line = reader.readText()
                call.respondText(line)
            }
        }

        get("/molecule/root") {
            val fileName = System.getProperty("user.dir") + "/server/src/main/kotlin/ru/aliexpress/fusion/molecules/root.json"

            val file = File(fileName)

            BufferedReader(FileReader(file)).use { reader ->
                val line = reader.readText()
                call.respondText(line)
            }
        }
    }
}