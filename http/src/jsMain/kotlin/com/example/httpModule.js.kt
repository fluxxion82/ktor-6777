package com.example

import io.ktor.client.engine.*
import io.ktor.client.engine.js.*

actual fun getEngine(): HttpClientEngineFactory<*> = Js
