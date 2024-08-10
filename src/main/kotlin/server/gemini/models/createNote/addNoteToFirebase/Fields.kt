package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import server.gemini.models.searchNotes.addSearchToFirebase.UserId

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
    val vectorIds: VectorIds? = VectorIds(),
    @SerialName("uId")
    val uId: UserId? = null,
)

@Serializable
data class Author(
    @SerialName("stringValue")
    val stringValue: String? = null
)

@Serializable
data class Category(
    @SerialName("stringValue")
    val stringValue: String? = null
)

@Serializable
data class IsEncrypted(
    @SerialName("booleanValue")
    val booleanValue: Boolean? = null
)

@Serializable
data class Priority(
    @SerialName("stringValue")
    val stringValue: String? = null
)

@Serializable
data class Tags(
    @SerialName("arrayValue")
    val arrayValue: ArrayValue? = null
)

@Serializable
data class ArrayValue(
    @SerialName("values")
    val values: List<Value?>? = null
)

@Serializable
data class Timestamp(
    @SerialName("integerValue")
    val integerValue: String? = null
)

@Serializable
data class Content(
    @SerialName("stringValue")
    val stringValue: String? = null
)

@Serializable
data class Title(
    @SerialName("stringValue")
    val stringValue: String? = null
)

@Serializable
data class Value(
    @SerialName("stringValue")
    val stringValue: String? = null
)

@Serializable
data class VectorIds(
    @SerialName("arrayValue")
    val arrayValue: ArrayValueX? = ArrayValueX()
)

@Serializable
data class ArrayValueX(
    @SerialName("values")
    val values: List<Value>? = listOf()
)