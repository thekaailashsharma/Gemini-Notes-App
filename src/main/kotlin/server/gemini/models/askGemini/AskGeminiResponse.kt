package server.gemini.models.askGemini


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AskGeminiResponse(
    @SerialName("candidates")
    val candidates: List<Candidate?>? = null
)