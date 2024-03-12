package com.example

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

const val CONNECT_TIMEOUT = 15L
const val READ_TIMEOUT = 15L
const val SECOND_MILLISECONDS = 1000L

val httpModule = module {
    single {
        FooClient(
            ktorHttpClient(
                baseUrl = "http://universities.hipolabs.com/search", // move path to client for iOS crash
            ),
        )
    }
}

fun ktorHttpClient(
    baseUrl: String,
    engine: HttpClientEngineFactory<*> = getEngine(),
): HttpClient {
    return HttpClient(engine) {
        install(ContentNegotiation) {
            json(
                json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                },
            )
        }
        install(Logging) {
            logger = NetworkLogger()
            level = LogLevel.ALL
        }

        defaultRequest {
            host = baseUrl.substringAfter("http://")
            url {
                protocol = URLProtocol.HTTP
            }
            contentType(ContentType.Application.Json)
        }

        install(HttpTimeout) {
            requestTimeoutMillis = CONNECT_TIMEOUT * SECOND_MILLISECONDS
            connectTimeoutMillis = CONNECT_TIMEOUT * SECOND_MILLISECONDS
            socketTimeoutMillis = READ_TIMEOUT * SECOND_MILLISECONDS
        }
    }
}

expect fun getEngine(): HttpClientEngineFactory<*>
