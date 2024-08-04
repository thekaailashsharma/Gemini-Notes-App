package server.gemini.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import server.gemini.routing.createNote
import server.gemini.routing.signUpUser

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        signUpUser()
        createNote()
    }
}
