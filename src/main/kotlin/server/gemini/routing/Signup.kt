package server.gemini.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import server.gemini.models.UserLoginRequest
import server.gemini.models.UserRegistrationRequest
import server.gemini.models.UserRegistrationResponse
import server.gemini.models.addFirestore.*
import server.gemini.models.signUpFirebase.SendVerificationBody
import server.gemini.models.signUpFirebase.SendVerificationResponse
import server.gemini.service.ApiServiceImpl

fun Routing.signUpUser() {
    val service by inject<ApiServiceImpl>()

    post("/signUp") {
        val request = call.receive<UserRegistrationRequest>()
        println("User is $request")
        val response = service.signUpFirebase(request)
        if (response.second == HttpStatusCode.OK) {
            println("User is inside")
            val sendVerificationBody = service.sendVerificationEmail(
                SendVerificationBody(
                    requestType = "VERIFY_EMAIL",
                    idToken = response.first.idToken
                )
            )
            if (sendVerificationBody.second == HttpStatusCode.OK) {
                call.respond(
                    SendVerificationResponse(
                        email = sendVerificationBody.first.email,
                        kind = sendVerificationBody.first.kind,
                        idToken = response.first.idToken,
                        userId = response.first.localId
                    )
                )
            } else {
                call.respond(
                    HttpStatusCode.BadRequest,
                    UserRegistrationResponse(
                        success = false,
                        message = response.first.error?.error?.message ?: "Something Went Wrong"
                    )
                )
            }
        } else {
            call.respond(
                HttpStatusCode.BadRequest,
                UserRegistrationResponse(
                    success = false,
                    message = response.first.error?.error?.message ?: "Something Went Wrong"
                )
            )
        }
    }

    get("/signIn") {
        val parameters = call.parameters
        parameters["email"]?.let { email ->
            parameters["password"]?.let { password ->
                val request = UserLoginRequest(email = email, password = password)
                val response = service.signInFirebase(request)
                if (response.second == HttpStatusCode.OK) {
                    call.respond(
                        response.second,
                        SendVerificationResponse(
                            email = response.first.email,
                            kind = response.first.kind,
                            idToken = response.first.idToken,
                            userId = response.first.localId
                        )
                    )
                } else {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        UserRegistrationResponse(
                            success = false,
                            message = response.first.error?.error?.message ?: "Something Went Wrong"
                        )
                    )
                }
            }
            call.respond(
                status = HttpStatusCode.BadRequest, UserRegistrationResponse(
                    success = false,
                    message = "Pass Password as parameter"
                )
            )
        }
        call.respond(
            status = HttpStatusCode.BadRequest, UserRegistrationResponse(
                success = false,
                message = "Pass Username or Password as parameters"
            )
        )
    }

    post("/verifyEmail") {
        val request = call.receive<SendVerificationBody>()
        val parameters = call.parameters
        val response = service.verifyVerificationEmail(idToken = request.idToken ?: "")

        parameters["email"]?.let { email ->
            try {
                if (response.second == HttpStatusCode.OK) {
                    println("Went Inside")
                    println("Response.first = ${response.first.users?.get(0)?.emailVerified}")
                    val isVerified = response.first.users?.filter {
                        println("Email Verified = ${it?.emailVerified} && ${it?.email} && ${email}")
                        it?.emailVerified == true && it.email == email
                    }
                    if (isVerified?.isEmpty() != true) {
                        val user = response.first.users?.first {
                            it?.email == email
                        }
                        val createCredentials = service.createUserCredentials(
                            idToken = request.idToken ?: "",
                            request = AddFireStoreRequest(
                                fields = FieldsX(
                                    email = EmailX(email),
                                    pass = PassX(user?.passwordHash),
                                    iD = IDX(user?.localId)
                                )
                            )
                        )
                        if (createCredentials.second == HttpStatusCode.OK) {
                            println("Went hereee")
                            call.respond(response.first)
                        } else {
                            call.respond(
                                status = response.second,
                                UserRegistrationResponse(
                                    success = false,
                                    message = response.first.error?.error?.message ?: "Something Went Wrong"
                                )
                            )
                        }
                    } else {
                        call.respond(
                            status = HttpStatusCode.BadRequest,
                            UserRegistrationResponse(
                                success = false,
                                message = "Verification Pending"
                            )
                        )
                    }
                } else {
                    println("Went Outside ${response.second}")
                    call.respond(
                        status = response.second,
                        UserRegistrationResponse(
                            success = false,
                            message = response.first.error?.error?.message ?: "Something Went Wrong"
                        )
                    )
                }
            } catch (e: Exception) {
                call.respond(
                    UserRegistrationResponse(
                        success = false,
                        message = response.first.error?.error?.message ?: "Something Went Wrong"
                    )
                )
            }

        }

    }
}