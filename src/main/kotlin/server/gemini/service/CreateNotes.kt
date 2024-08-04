package server.gemini.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import server.gemini.models.createNote.CreateNoteRequest
import server.gemini.models.createVectors.Content
import server.gemini.models.createVectors.CreateVectorsRequest
import server.gemini.models.createVectors.CreateVectorsResponse
import server.gemini.models.createVectors.Part
import server.gemini.models.signUpFirebase.SignUpFirebaseError
import server.gemini.models.upsert.Metadata
import server.gemini.models.upsert.UpsertVectorsRequest
import server.gemini.models.upsert.UpsertVectorsResponse
import server.gemini.models.upsert.Vector
import server.gemini.utils.Paragraph
import server.gemini.utils.splitIntoParagraphs
import java.util.*

class CreateNotes(private val client: HttpClient) {
    fun createParagraphs(content: String): List<Paragraph> {
        return content.splitIntoParagraphs()
    }

    suspend fun createVectors(paragraphs: List<Paragraph>): List<Vector?> {
        val vectorsList: MutableList<Vector?> = mutableListOf()
        paragraphs.forEach { paragraph ->
            println(paragraph.text)
            try {
                val createVectorUrl = "https://generativelanguage.googleapis.com/v1beta/models/text-embedding-004:" +
                        "embedContent?key=AIzaSyD1dFIesRY8KavV0CWde_wkk-mkXL0fUc8"
                val c = client.post {
                    url(createVectorUrl)
                    setBody(
                        CreateVectorsRequest(
                            content = Content(
                                parts = listOf(
                                    Part(paragraph.text)
                                )
                            ),
                            model = "models/text-embedding-004"
                        )
                    )
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                    headers {
                        append("Accept", "*/*")
                        append("Content-Type", "application/json")
                    }
                }
                println("Response is ${c}")
                if (c.status.isSuccess()) {
                    println("Went Insidessss")
                    val body = c.body<CreateVectorsResponse>()
                    val vectors = body.embedding?.values
                    vectorsList.add(
                        Vector(
                            id = UUID.randomUUID().toString(),
                            metadata = Metadata(
                                paragraph.text
                            ),
                            values = vectors,
                            namespace = "paragraph"
                        )
                    )
                } else {
                    println("Went Insides ${c.body<CreateVectorsResponse>()}")
                }
            } catch (e: Exception) {
                println("Error is ${e.message}")
            }
        }
        return vectorsList
    }

    suspend fun upsertVectors(upsertVectorsRequests: List<UpsertVectorsRequest>): UpsertVectorsResponse {
        val response = UpsertVectorsResponse(
            upsertedCount = null
        )
        var count = 0
        try {
            upsertVectorsRequests.forEach { upsertVectorsRequest ->
                println("Request is $upsertVectorsRequest")
                val addFireStoreUrl = "https://temporary-m7240u6.svc.aped-4627-b74a.pinecone.io/vectors/upsert"
                val c = client.post {
                    url(addFireStoreUrl)
                    setBody(
                        upsertVectorsRequest
                    )
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                    headers {
                        append("Api-Key", "e88fe59a-4d45-4e71-a287-90a5088db545")
                        append("X-Pinecone-API-Version", "2024-07")
                        append("Accept", "*/*")
                        append("content-Type", "application/json")
                    }
                }
                println("UpsertResponse is ${c}")
                if (c.status.isSuccess()) {
                    println("UpsertResponse is inside")
                    val body = c.body<UpsertVectorsResponse>()
                    count += body.upsertedCount ?: 0
                } else {
                    println("UpsertResponse is ouside")
                    UpsertVectorsResponse(
                        upsertedCount = null
                    )
                }
            }
            return UpsertVectorsResponse(
                upsertedCount = count
            )
        } catch (e: Exception) {
            println("UpsertResponse is ${e.message}")
            return UpsertVectorsResponse(
                upsertedCount = null
            )
        }
    }
}