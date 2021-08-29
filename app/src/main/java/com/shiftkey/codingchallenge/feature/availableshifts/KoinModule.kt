package com.shiftkey.codingchallenge.feature.availableshifts

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {
   single { GetPagedAvailableShiftsUseCase(get()) }
   single { AvailableShiftsPagingSource(get()) }
   viewModel { AvailableShiftsViewModel(get()) }
}
