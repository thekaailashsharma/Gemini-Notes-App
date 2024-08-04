package server.gemini.models.createVectors


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateVectorsRequest(
    @SerialName("content")
    val content: Content? = null,
    @SerialName("model")
    val model: String? = null
)