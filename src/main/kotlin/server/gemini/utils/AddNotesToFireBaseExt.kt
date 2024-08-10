package server.gemini.utils

import server.gemini.models.createNote.CreateNoteRequest
import server.gemini.models.createNote.addNoteToFirebase.AddNoteFirebaseRequest
import server.gemini.models.createNote.addNoteToFirebase.*
import server.gemini.models.createNote.addNoteToFirebase.Fields
import server.gemini.models.searchNotes.addSearchToFirebase.UserId
import server.gemini.models.upsert.Vector

fun CreateNoteRequest.toAddNoteFirebaseRequest(vectors: List<Vector?>): AddNoteFirebaseRequest {
    return AddNoteFirebaseRequest(
        fields = Fields(
            author = this.author?.let { Author(stringValue = it) },
            category = this.category?.let { Category(stringValue = it) },
            content = this.content?.let { Content(stringValue = it) },
            isEncrypted = this.isEncrypted?.let { IsEncrypted(booleanValue = it) },
            priority = this.priority?.let { Priority(stringValue = it) },
            tags = this.tags?.let { tagList ->
                Tags(
                    arrayValue = ArrayValue(
                        values = tagList.map { it?.let { Value(stringValue = it) } }
                    )
                )
            },
            timestamp = this.timestamp?.let { Timestamp(integerValue = it.toString()) },
            title = this.title?.let { Title(stringValue = it) },
            vectorIds = VectorIds(
                arrayValue = ArrayValueX(
                    values = vectors.map {
                        Value(stringValue = it?.id)
                    }
                )
            ),
            uId = this.uId?.let { UserId(stringValue = it) }

        )
    )
}
