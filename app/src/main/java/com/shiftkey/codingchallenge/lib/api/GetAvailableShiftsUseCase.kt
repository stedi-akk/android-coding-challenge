package com.shiftkey.codingchallenge.lib.api

import com.shiftkey.codingchallenge.lib.domain.Shift
import com.shiftkey.codingchallenge.lib.network.RetrofitFactory
import io.reactivex.Observable
import io.reactivex.Single
import org.threeten.bp.LocalDate

private const val ADDRESS = "Dallas, TX"

class GetAvailableShiftsUseCase(
   private val retrofitFactory: RetrofitFactory
) {

   fun get(plusWeeks: Long): Single<List<Shift>> {
      return retrofitFactory.create(ShiftsApi.BASE_URL)
         .create(ShiftsApi::class.java)
         .getAvailableShifts(
            start = LocalDate.now().plusWeeks(plusWeeks),
            type = ShiftsApi.AvailableShiftsType.WEEK,
            address = ADDRESS,
            radius = null
         )
         .flatMapObservable { Observable.fromIterable(it.data) }
         .filter { it.date >= LocalDate.now() }
         .filter { it.shifts.isNotEmpty() }
         .flatMap { Observable.fromIterable(it.shifts) }
         .toList()
   }
}
