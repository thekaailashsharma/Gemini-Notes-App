package server.gemini.models.searchNotes.addSearchToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fields(
    @SerialName("conversations")
    val conversations: Conversations? = Conversations(),
    @SerialName("userId")
    val userId: UserId? = UserId()
)

@Serializable
data class Conversations(
    @SerialName("arrayValue")
    val arrayValue: ArrayValue? = ArrayValue()
)

@Serializable
data class UserId(
    @SerialName("stringValue")
    val stringValue: String? = null
)

@Serializable
data class ArrayValue(
    @SerialName("values")
    val values: List<Value>? = listOf()
)

@Serializable
data class FieldsX(
    @SerialName("answer")
    val answer: Answer? = null,
    @SerialName("question")
    val question: Question? = null,
    @SerialName("timestamp")
    val timestamp: Timestamp? = null
)

@Serializable
data class MapValue(
    @SerialName("fields")
    val fields: FieldsX? = FieldsX()
)

@Serializable
data class Question(
    @SerialName("stringValue")
    val stringValue: String? = null
)

@Serializable
data class Timestamp(
    @SerialName("integerValue")
    val integerValue: Long? = null
)

@Serializable
data class Value(
    @SerialName("mapValue")
    val mapValue: MapValue? = MapValue()
)

@Serializable
data class Answer(
    @SerialName("stringValue")
    val stringValue: String? = null
)

