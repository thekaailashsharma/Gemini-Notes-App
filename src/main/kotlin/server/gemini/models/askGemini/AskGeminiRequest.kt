package server.gemini.models.askGemini


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AskGeminiRequest(
    @SerialName("prompt")
    val prompt: Prompt? = null
)