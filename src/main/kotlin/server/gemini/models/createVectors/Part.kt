package server.gemini.models.createVectors


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Part(
    @SerialName("text")
    val text: String? = null
)