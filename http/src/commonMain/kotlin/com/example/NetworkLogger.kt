package com.example

class NetworkLogger : io.ktor.client.plugins.logging.Logger {
    override fun log(message: String) {
        println(message)
    }
}
