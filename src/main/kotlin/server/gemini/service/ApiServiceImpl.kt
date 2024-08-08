package server.gemini.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.headers
import io.ktor.http.*
import server.gemini.models.UserLoginRequest
import server.gemini.models.UserRegistrationRequest
import server.gemini.models.UserRegistrationResponse
import server.gemini.models.addFirestore.AddFireStoreRequest
import server.gemini.models.addFirestore.FireStoreResponse
import server.gemini.models.createNote.CreateNoteRequest
import server.gemini.models.queryVectors.QueryVectorsRequest
import server.gemini.models.searchNotes.SearchNotesResponse
import server.gemini.models.searchNotes.SearchRequest
import server.gemini.models.signInResponse.SignInFirebaseResponse
import server.gemini.models.signUpFirebase.*
import server.gemini.models.upsert.Metadata
import server.gemini.models.upsert.UpsertVectorsRequest
import server.gemini.models.upsert.Vector
import server.gemini.utils.ApiKeyNotFoundException
import server.gemini.utils.Paragraph
import server.gemini.utils.toCombinedString
import java.util.*

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    private val apiKey = System.getenv("API_KEY") ?: throw ApiKeyNotFoundException(
        message = "Please Enter an ApiKey",
        errorCode = 400
    )
    private val projectId = System.getenv("PROJECT_ID") ?: throw ApiKeyNotFoundException(
        message = "Please Enter an ProjectId",
        errorCode = 400
    )

    val createNotes = CreateNotes(client)

    override suspend fun ifEmailExists(email: String): Pair<Boolean, HttpStatusCode> {
        TODO("Not yet implemented")
    }

    override suspend fun signUpFirebase(request: UserRegistrationRequest): Pair<SignUpFirebaseResponse, HttpStatusCode> {
        try {
            val signUpUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=$apiKey"
            val c = client.post {
                url(signUpUrl)
                setBody(
                    SignUpFirebaseAuth(
                        email = request.email,
                        password = request.password,
                        returnSecureToken = true
                    )
                )
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                headers {
                    append("Accept", "*/*")
                    append("Content-Type", "application/json")
                }
            }

            println("Token is ${c.body<SignUpFirebaseResponse>()}")
            return if (c.status.isSuccess()) {
                Pair(c.body<SignUpFirebaseResponse>(), c.status)
            } else {
                val error = c.body<SignUpFirebaseError>()
                Pair(
                    SignUpFirebaseResponse(
                        error = SignUpFirebaseError(
                            error = Error(
                                message = error.error?.message
                            )
                        )
                    ),
                    c.status
                )
            }
        } catch (e: Exception) {
            println("Error during Firebase signup: $e")
            return Pair(
                SignUpFirebaseResponse(
                    error = SignUpFirebaseError(
                        error = Error(
                            message = "Something Went Wrong"
                        )
                    )
                ),
                HttpStatusCode.ServiceUnavailable
            )
        }
    }

    override suspend fun sendVerificationEmail(sendVerificationBody: SendVerificationBody): Pair<SendVerificationResponse, HttpStatusCode> {
        val sendVerificationUrl = "https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key=$apiKey"
        val c = client.post {
            url(sendVerificationUrl)
            setBody(
                sendVerificationBody
            )
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            headers {
                append("Accept", "*/*")
                append("Content-Type", "application/json")
            }
        }

        println("Token Verification is ${c}")
        return Pair(c.body<SendVerificationResponse>(), c.status)
    }

    override suspend fun verifyVerificationEmail(idToken: String): Pair<VerifyVerificationResponse, HttpStatusCode> {
        try {
            val sendVerificationUrl = "https://identitytoolkit.googleapis.com/v1/accounts:lookup?key=$apiKey"
            val c = client.post {
                url(sendVerificationUrl)
                setBody(
                    VerifyVerificationBody(
                        idToken = idToken,
                    )
                )
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                headers {
                    append("Accept", "*/*")
                    append("Content-Type", "application/json")
                }
            }

            println("Token VerificationVerify is ${c.body<VerifyVerificationResponse>()}")
            return if (c.status.isSuccess()) {
                println("Went Inside")
                Pair(c.body<VerifyVerificationResponse>(), c.status)
            } else {
                val error = c.body<SignUpFirebaseError>()
                Pair(
                    VerifyVerificationResponse(
                        error = SignUpFirebaseError(
                            error = Error(
                                message = error.error?.message
                            )
                        )
                    ),
                    c.status
                )
            }
        } catch (e: Exception) {
            println("Error during Firebase signup: $e")
            return Pair(
                VerifyVerificationResponse(
                    error = SignUpFirebaseError(
                        error = Error(
                            message = "Something Went Wrong"
                        )
                    )
                ),
                HttpStatusCode.ServiceUnavailable
            )
        }
    }

    override suspend fun createUserCredentials(
        idToken: String,
        request: AddFireStoreRequest
    ): Pair<FireStoreResponse, HttpStatusCode> {
        try {
            val addFireStoreUrl = "https://firestore.googleapis.com/v1/projects/$projectId/" +
                    "databases/(default)/documents/userCredentials/${request.fields?.iD?.stringValue}?updateMask.fieldPaths=name&" +
                    "updateMask.fieldPaths=pass&updateMask.fieldPaths=email"
            val c = client.patch {
                url(addFireStoreUrl)
                setBody(
                    request
                )
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $idToken")
                    append("Accept", "*/*")
                    append("Content-Type", "application/json")
                }
            }
            println("Response is ${c}")
            return if (c.status.isSuccess()) {
                println("Went Insidessss")
                Pair(c.body<FireStoreResponse>(), c.status)
            } else {
                println("Went Insides ${c.body<SignUpFirebaseError>()}")
                val error = c.body<SignUpFirebaseError>()
                Pair(
                    FireStoreResponse(
                        error = SignUpFirebaseError(
                            error = Error(
                                message = error.error?.message
                            )
                        )
                    ),
                    c.status
                )
            }
        } catch (e: Exception) {
            return Pair(
                FireStoreResponse(
                    error = SignUpFirebaseError(
                        error = Error(
                            message = "Something Went Wrong"
                        )
                    )
                ),
                HttpStatusCode.ServiceUnavailable
            )
        }
    }


    override suspend fun signInFirebase(request: UserLoginRequest): Pair<SignInFirebaseResponse, HttpStatusCode> {
        try {
            val signUpUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?" +
                    "key=$apiKey"
            val c = client.post {
                url(signUpUrl)
                setBody(
                    SignUpFirebaseAuth(
                        email = request.email,
                        password = request.password,
                        returnSecureToken = true
                    )
                )
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                headers {
                    append("Accept", "*/*")
                    append("Content-Type", "application/json")
                }
            }

            println("Login Token is ${c}")
            return if (c.status.isSuccess()) {
                println("Went Insidessss")
                Pair(c.body<SignInFirebaseResponse>(), c.status)
            } else {
                println("Went Insides ${c.body<SignUpFirebaseError>()}")
                val error = c.body<SignUpFirebaseError>()
                Pair(
                    SignInFirebaseResponse(
                        error = SignUpFirebaseError(
                            error = Error(
                                message = error.error?.message
                            )
                        )
                    ),
                    c.status
                )
            }
        } catch (e: Exception) {
            println("Complete signup responses is ${e.message}")
            return Pair(
                SignInFirebaseResponse(
                    error = SignUpFirebaseError(
                        error = Error(
                            message = "Something Went Wrong"
                        )
                    )
                ),
                HttpStatusCode.ServiceUnavailable
            )
        }
    }

    override suspend fun createNote(request: CreateNoteRequest): Pair<UserRegistrationResponse, HttpStatusCode> {
        val paragraphs = createNotes.createParagraphs(request.content ?: "")
        val vectors = createNotes.createVectors(paragraphs)
        val notes = createNotes.upsertVectors(
            upsertVectorsRequests = vectors.map {
                UpsertVectorsRequest(
                    vectors = vectors
                )
            }
        )
        println("notes is $notes")
        return Pair(
            UserRegistrationResponse(
                success = true,
                message = "Note Created Successfully count - ${notes.upsertedCount}"
            ),
            HttpStatusCode.OK
        )
    }

    override suspend fun searchNotes(searchRequest: SearchRequest): Pair<SearchNotesResponse, HttpStatusCode> {
        try {
            val vector = createNotes.createVectors(
                listOf(
                    Paragraph(
                        text = searchRequest.content ?: "",
                        namespace = searchRequest.namespace ?: ""
                    )
                )
            )
            val justVectors = vector
                .mapNotNull { it?.values }
                .flatten()

            val queryNotes = createNotes.queryVectorsRequest(
                QueryVectorsRequest(
                    vector = justVectors,
                    includeMetadata = true,
                    topK = 3,
                    includeValues = true,
                )
            )

            val content = queryNotes.matches
                ?.map { it?.metadata?.genre }
                .toCombinedString()

            val askGemini = createNotes.askGemini(
                question = searchRequest.content ?: "",
                content = content
            )
            if (askGemini.response != null) {
                return Pair(
                    askGemini,
                    HttpStatusCode.OK
                )
            } else {
                return Pair(
                    SearchNotesResponse(
                        response = "No Notes Found"
                    ),
                    HttpStatusCode.BadRequest
                )
            }
        } catch (e: Exception) {
            println("Error during searchNotes: $e")
            return Pair(
                SearchNotesResponse(
                    response = "Something Went Wrong"
                ),
                HttpStatusCode.ServiceUnavailable
            )
        }
    }
}
