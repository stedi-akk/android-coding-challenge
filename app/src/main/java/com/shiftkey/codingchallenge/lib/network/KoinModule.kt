package com.shiftkey.codingchallenge.lib.network

import org.koin.dsl.module

val koinModule = module {
   single { RetrofitFactory() }
}
