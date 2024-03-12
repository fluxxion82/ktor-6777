package com.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    println("start main")
    val app = App()
    val args = MutableStateFlow("")

    onWasmReady {
        CanvasBasedWindow("ktor6777") {
            val scope = rememberCoroutineScope()
            LaunchedEffect("foo") {
                scope.launch {
                    println("get response")
                    app.fooClient.getFooResponse()
                        .onSuccess {
                            println("success: ${it.first().name}")
                            args.value = "foo id ${it.first().name}"
                        }
                        .onFailure {
                            println("error: ${it.message}")
                            args.value = "error: ${it.message}"
                        }
                }
            }

            val fooId by args.collectAsState()
            Column(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material.Text(fooId)
            }
        }
    }
}

fun appModule() = listOf(
    httpModule,
)

class App: KoinComponent {
    val fooClient: FooClient by inject()

    init {
        GlobalContext.startKoin {
            modules(appModule())
        }
    }
}
