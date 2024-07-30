package server.gemini.authentication

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import org.koin.ktor.ext.inject
import server.gemini.service.ApiServiceImpl

fun Application.userAuthentication() {
    val service by inject<ApiServiceImpl>()

    install(Authentication) {
        bearer("Authorization") {
            authenticate { tokenCredential ->
                val client = service.verifyVerificationEmail(tokenCredential.token)
                if (client.second == HttpStatusCode.OK && client.first.error == null) {
                    UserIdPrincipal(tokenCredential.token)
                } else {
                    null
                }
            }
        }
    }
}