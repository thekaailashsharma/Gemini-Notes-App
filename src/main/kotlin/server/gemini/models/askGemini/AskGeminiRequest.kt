package server.gemini.models.askGemini


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AskGeminiRequest (
    val contents: List<Content>? = null
)

@Serializable
data class Content (
    val parts: List<Part>? = null
)

@Serializable
data class Part (
    val text: String? = null
)
