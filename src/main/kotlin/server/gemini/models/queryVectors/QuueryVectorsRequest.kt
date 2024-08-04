package server.gemini.models.queryVectors


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryVectorsRequest(
    @SerialName("includeMetadata")
    val includeMetadata: Boolean? = null,
    @SerialName("includeValues")
    val includeValues: Boolean? = null,
    @SerialName("namespace")
    val namespace: String? = null,
    @SerialName("topK")
    val topK: Int? = null,
    @SerialName("vector")
    val vector: List<Double?>? = null
)