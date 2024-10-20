package server.gemini.models.askGemini


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AskGeminiResponse(
    @SerialName("candidates")
    val candidates: List<Candidate?>? = null,
    val usageMetadata: UsageMetadata? = null
)

@Serializable
data class UsageMetadata (
    val promptTokenCount: Long? = null,
    val candidatesTokenCount: Long? = null,
    val totalTokenCount: Long? = null
)