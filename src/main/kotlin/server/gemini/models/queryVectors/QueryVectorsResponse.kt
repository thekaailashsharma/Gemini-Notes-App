package server.gemini.models.queryVectors


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryVectorsResponse(
    @SerialName("matches")
    val matches: List<Matche?>? = null,
    @SerialName("namespace")
    val namespace: String? = null,
    @SerialName("results")
    val results: List<String?>? = null,
    @SerialName("usage")
    val usage: Usage? = null
)