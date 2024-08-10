package server.gemini.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import server.gemini.models.createNote.CreateNoteRequest
import server.gemini.models.searchNotes.SearchRequest
import server.gemini.service.ApiServiceImpl

fun Routing.searchNote() {

    val service by inject<ApiServiceImpl>()

    authenticate("Authorization") {
        post("/searchNote") {
            val request = call.receive<SearchRequest>()
            val principal = call.principal<UserIdPrincipal>()
            println("Note is $request")
            val response = service.searchNotes(request, idToken = principal?.name ?: "")
            if (response.second == HttpStatusCode.OK) {
                call.respond(response.first)
            } else {
                call.respond(HttpStatusCode.BadRequest, response.first)
            }
        }
    }

}