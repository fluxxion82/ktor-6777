package com.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext

fun main() {
    val app = App()
    val args = MutableStateFlow("")

    singleWindowApplication(
        title = "Ktor6777",
        state = WindowState(size = DpSize(500.dp, 800.dp))
    ) {
        val scope = rememberCoroutineScope()
        LaunchedEffect("foo") {
            scope.launch {
                app.fooClient.getFooResponse()
                    .onSuccess {
                        args.value = "foo id ${it.first().name}"
                    }
                    .onFailure {
                        args.value = "error: ${it.message}"
                    }
            }
        }

        val fooId by args.collectAsState()
        Column(modifier = Modifier.fillMaxSize()) {
            Text(fooId)
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

