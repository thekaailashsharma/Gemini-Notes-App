package server.gemini.utils

import server.gemini.models.searchNotes.SearchNotesResponse
import server.gemini.models.searchNotes.addSearchToFirebase.*

fun SearchNotesResponse.toSearchNotesFirebaseRequest(userId: String): SearchNoteRequest {
    return SearchNoteRequest(
        fields = Fields(
            conversations = Conversations(
                arrayValue = ArrayValue(
                    values = listOf(
                        Value(
                            mapValue = MapValue(
                                fields = FieldsX(
                                    question = Question(stringValue = this.name),
                                    answer = this.response?.let { Answer(stringValue = it) },
                                    timestamp = Timestamp(System.currentTimeMillis())
                                ),
                            )
                        )
                    )
                ),
            ),
            userId = UserId(stringValue = userId)
        )
    )
}

fun SearchFireStoreResponse.appendSearchNotesResponse(searchNotesResponse: SearchNotesResponse): SearchFireStoreResponse {
    val searchNoteRequest = searchNotesResponse.toSearchNotesFirebaseRequest(userId = this.fields?.userId?.stringValue.orEmpty())

    val existingValues = this.fields?.conversations?.arrayValue?.values.orEmpty()

    val newValue = searchNoteRequest.fields?.conversations?.arrayValue?.values?.firstOrNull()

    val updatedValues = existingValues.toMutableList().apply {
        newValue?.let { add(it) }
    }

    return this.copy(
        fields = this.fields?.copy(
            conversations = this.fields.conversations?.copy(
                arrayValue = ArrayValue(
                    values = updatedValues
                )
            ),
            userId = this.fields.userId
        ),

    )
}
