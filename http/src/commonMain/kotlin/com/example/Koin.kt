package com.example

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

// for ios
fun initKoin(appModule: Module): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            httpModule
        )
    }

    return koinApplication
}
