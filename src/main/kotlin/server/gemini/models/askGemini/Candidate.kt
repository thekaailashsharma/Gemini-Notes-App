package server.gemini.models.askGemini


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Candidate(
    val content: Content? = null,
    val finishReason: String? = null,
    val index: Long? = null,
    val safetyRatings: List<SafetyRating>? = null
)