package com.example

import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module

fun initKoinIos(): KoinApplication = initKoin(
    module {

    }
)

// Called from Swift
object KotlinDependencies : KoinComponent {
    val fooClient: FooClient by inject()
}
