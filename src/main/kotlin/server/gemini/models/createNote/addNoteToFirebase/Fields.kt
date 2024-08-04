package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fields(
    @SerialName("author")
    val author: Author? = Author(),
    @SerialName("category")
    val category: Category? = Category(),
    @SerialName("content")
    val content: Content? = Content(),
    @SerialName("isEncrypted")
    val isEncrypted: IsEncrypted? = IsEncrypted(),
    @SerialName("priority")
    val priority: Priority? = Priority(),
    @SerialName("tags")
    val tags: Tags? = Tags(),
    @SerialName("timestamp")
    val timestamp: Timestamp? = Timestamp(),
    @SerialName("title")
    val title: Title? = Title(),
    @SerialName("vectorIds")
    val vectorIds: VectorIds? = VectorIds()
)