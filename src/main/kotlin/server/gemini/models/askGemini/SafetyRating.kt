package server.gemini.models.askGemini


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SafetyRating(
    @SerialName("category")
    val category: String? = null,
    @SerialName("probability")
    val probability: String? = null
)