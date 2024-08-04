package server.gemini.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import server.gemini.models.createNote.CreateNoteRequest
import server.gemini.service.ApiServiceImpl

fun Routing.createNote() {

    val service by inject<ApiServiceImpl>()

    authenticate("Authorization") {
        post("/createNote") {
            val request = call.receive<CreateNoteRequest>()
            println("Note is $request")
            val response = service.createNote(request)
            if (response.second == HttpStatusCode.OK) {
                call.respond(response.first)
            } else {
                call.respond(HttpStatusCode.BadRequest, response.first)
            }
        }
    }
}