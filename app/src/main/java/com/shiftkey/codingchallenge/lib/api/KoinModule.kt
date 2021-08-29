package com.shiftkey.codingchallenge.lib.api

import org.koin.dsl.module

val koinModule = module {
   single { GetAvailableShiftsUseCase(get()) }
}
