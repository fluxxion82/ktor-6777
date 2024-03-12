package com.example

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual fun getEngine(): HttpClientEngineFactory<*> = OkHttp
