package server.gemini.service

import server.gemini.models.signUpFirebase.SendVerificationBody
import server.gemini.models.signUpFirebase.SendVerificationResponse
import server.gemini.models.signUpFirebase.SignUpFirebaseResponse
import server.gemini.models.signUpFirebase.VerifyVerificationResponse
import server.gemini.models.addFirestore.FireStoreResponse
import io.ktor.http.*
import server.gemini.models.UserLoginRequest
import server.gemini.models.UserRegistrationRequest
import server.gemini.models.addFirestore.AddFireStoreRequest
import server.gemini.models.signInResponse.SignInFirebaseResponse

interface ApiService {

    suspend fun ifEmailExists(email: String): Pair<Boolean, HttpStatusCode>

    suspend fun signUpFirebase(request: UserRegistrationRequest): Pair<SignUpFirebaseResponse, HttpStatusCode>

    suspend fun sendVerificationEmail(sendVerificationBody: SendVerificationBody): Pair<SendVerificationResponse, HttpStatusCode>

    suspend fun verifyVerificationEmail(idToken: String): Pair<VerifyVerificationResponse, HttpStatusCode>

    suspend fun createUserCredentials(
        idToken: String,
        request: AddFireStoreRequest
    ): Pair<FireStoreResponse, HttpStatusCode>

    suspend fun signInFirebase(request: UserLoginRequest): Pair<SignInFirebaseResponse, HttpStatusCode>

}