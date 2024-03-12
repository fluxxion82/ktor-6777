//
//  Koin.swift
//  iosApp
//
//  Created by Sterling Albury on 3/11/24.
//  Copyright © 2024 org.jetbrains. All rights reserved.
//

import Foundation
import http

func startKoin() {
    let koinApplication = KoinIosKt.doInitKoinIos()
    _koin = koinApplication.koin
}

private var _koin: Koin_coreKoin?
var koin: Koin_coreKoin {
    return _koin!
}
