package server.gemini.models.askGemini


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prompt(
    @SerialName("text")
    val text: String? = null
)