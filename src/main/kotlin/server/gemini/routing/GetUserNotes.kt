package server.gemini.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import server.gemini.models.createNote.addNoteToFirebase.GetNotesResponse
import server.gemini.models.signUpFirebase.SignUpFirebaseError
import server.gemini.service.ApiServiceImpl

fun Routing.getUserNotes() {
    val service by inject<ApiServiceImpl>()

    authenticate("Authorization") {
        get("/getUserNotes") {
            val principal = call.principal<UserIdPrincipal>()
            val parameters = call.parameters
            parameters["userId"]?.let { userId ->
                val response = service.getUserNotes(idToken = principal?.name ?: "", userId = userId)
                if (response.second == HttpStatusCode.OK) {
                    call.respond(GetNotesResponse(
                        documents = response.first,
                    ))
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Something went wrong")
                }
            }
        }
    }
}