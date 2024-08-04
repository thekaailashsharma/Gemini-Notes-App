package server.gemini.models.askGemini


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Candidate(
    @SerialName("output")
    val output: String? = null,
    @SerialName("safetyRatings")
    val safetyRatings: List<SafetyRating?>? = null
)