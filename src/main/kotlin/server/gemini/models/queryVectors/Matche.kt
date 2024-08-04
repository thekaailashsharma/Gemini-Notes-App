package server.gemini.models.queryVectors


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Matche(
    @SerialName("id")
    val id: String? = null,
    @SerialName("metadata")
    val metadata: Metadata? = null,
    @SerialName("score")
    val score: Double? = null,
    @SerialName("values")
    val values: List<Double?>? = null
)