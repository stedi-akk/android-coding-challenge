package com.shiftkey.codingchallenge.feature.availableshifts

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.shiftkey.codingchallenge.lib.api.GetAvailableShiftsUseCase
import com.shiftkey.codingchallenge.lib.domain.Shift
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class AvailableShiftsPagingSource(
   private val getAvailableShiftsUseCase: GetAvailableShiftsUseCase
) : RxPagingSource<Long, Shift>() {

   override fun getRefreshKey(state: PagingState<Long, Shift>): Long? {
      return state.anchorPosition?.let {
         val anchorPage = state.closestPageToPosition(it)
         anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
      }
   }

   override fun loadSingle(params: LoadParams<Long>): Single<LoadResult<Long, Shift>> {
      val plusWeeks = params.key ?: 0L
      return getAvailableShiftsUseCase.get(plusWeeks)
         .subscribeOn(Schedulers.io())
         .map {
            println("Received ${it.size} shifts with plusWeeks=$plusWeeks")
            toLoadResult(it, plusWeeks)
         }
         .onErrorReturn {
            it.printStackTrace()
            LoadResult.Error(it)
         }
   }

   private fun toLoadResult(shifts: List<Shift>, plusWeeks: Long): LoadResult<Long, Shift> {
      return LoadResult.Page(
         data = shifts,
         prevKey = null,
         nextKey = plusWeeks + 1
      )
   }
}
