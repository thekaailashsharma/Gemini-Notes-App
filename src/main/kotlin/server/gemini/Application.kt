package server.gemini

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import server.gemini.authentication.userAuthentication
import server.gemini.di.httpClientModule
import server.gemini.plugins.configureMonitoring
import server.gemini.plugins.configureRouting
import server.gemini.plugins.configureSerialization

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        port = 8085,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    install(Koin) {
        slf4jLogger()
        modules(httpClientModule)
    }
    userAuthentication()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}