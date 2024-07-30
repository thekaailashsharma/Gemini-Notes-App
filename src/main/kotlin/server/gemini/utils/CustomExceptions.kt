package server.gemini.utils

class ApiKeyNotFoundException(
    message: String,
    val errorCode: Int
) : Exception(message)