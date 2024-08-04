package server.gemini.models.upsert


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vector(
    @SerialName("id")
    val id: String? = null,
    @SerialName("metadata")
    val metadata: Metadata? = null,
    @SerialName("values")
    val values: List<Double?>? = null,
    @SerialName("namespace")
    val namespace: String? = null
)