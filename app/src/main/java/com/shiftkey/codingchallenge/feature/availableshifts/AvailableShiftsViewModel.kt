package com.shiftkey.codingchallenge.feature.availableshifts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.shiftkey.codingchallenge.lib.domain.Shift
import io.reactivex.disposables.Disposable

class AvailableShiftsViewModel(
   private val getPagedAvailableShiftsUseCase: GetPagedAvailableShiftsUseCase
) : ViewModel() {

   private var getDisposable: Disposable? = null

   val pagedAvailableShifts = MutableLiveData<PagingData<Shift>>()

   fun getPagedAvailableShifts() {
      getDisposable?.dispose()
      getDisposable = getPagedAvailableShiftsUseCase.get()
         .cachedIn(viewModelScope)
         .subscribe(
            { pagedAvailableShifts.value = it },
            { it.printStackTrace() }
         )
   }

   override fun onCleared() {
      getDisposable?.dispose()
   }
}
