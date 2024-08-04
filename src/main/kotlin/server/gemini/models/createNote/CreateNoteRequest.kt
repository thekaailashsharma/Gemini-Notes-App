package server.gemini.models.createNote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateNoteRequest(
    @SerialName("author")
    val author: String? = null,
    @SerialName("category")
    val category: String? = null,
    @SerialName("content")
    val content: String? = null,
    @SerialName("isEncrypted")
    val isEncrypted: Boolean? = null,
    @SerialName("priority")
    val priority: String? = null,
    @SerialName("tags")
    val tags: List<String?>? = null,
    @SerialName("timestamp")
    val timestamp: Long? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("uId")
    val uId: String? = null
)