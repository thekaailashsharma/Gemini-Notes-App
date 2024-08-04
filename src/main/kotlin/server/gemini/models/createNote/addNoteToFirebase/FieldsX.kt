package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldsX(
    @SerialName("accommodations")
    val accommodations: Accommodations? = Accommodations(),
    @SerialName("createdAt")
    val createdAt: CreatedAt? = CreatedAt(),
    @SerialName("endDate")
    val endDate: EndDate? = EndDate(),
    @SerialName("from")
    val from: From? = From(),
    @SerialName("startDate")
    val startDate: StartDate? = StartDate(),
    @SerialName("to")
    val to: To? = To(),
    @SerialName("travelType")
    val travelType: TravelType? = TravelType(),
    @SerialName("tripName")
    val tripName: TripName? = TripName()
)