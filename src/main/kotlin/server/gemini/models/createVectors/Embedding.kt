package server.gemini.models.createVectors


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Embedding(
    @SerialName("values")
    val values: List<Double?>? = null
)