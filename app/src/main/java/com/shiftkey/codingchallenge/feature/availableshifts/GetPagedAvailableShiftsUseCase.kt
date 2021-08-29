package com.shiftkey.codingchallenge.feature.availableshifts

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import com.shiftkey.codingchallenge.lib.domain.Shift
import io.reactivex.Observable

class GetPagedAvailableShiftsUseCase(
   private val pagingSource: AvailableShiftsPagingSource
) {

   fun get(): Observable<PagingData<Shift>> {
      return Pager(
         config = PagingConfig(
            pageSize = 20,
         ),
         pagingSourceFactory = { pagingSource }
      ).observable
   }
}
