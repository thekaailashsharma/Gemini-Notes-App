package server.gemini.utils

class ApiKeyNotFoundException(
    message: String,
    val errorCode: Int
) : Exception(message)

fun List<String?>?.toCombinedString(): String {
    return this
        ?.filterNotNull()
        ?.joinToString(separator = ". ")
        ?: ""
}
