package server.gemini.models.createVectors


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateVectorsResponse(
    @SerialName("embedding")
    val embedding: Embedding? = null
)