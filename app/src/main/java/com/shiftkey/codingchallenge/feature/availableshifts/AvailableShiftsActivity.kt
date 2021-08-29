package com.shiftkey.codingchallenge.feature.availableshifts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.shiftkey.codingchallenge.databinding.AvailableShiftsActivityBinding
import com.shiftkey.codingchallenge.feature.shiftdetails.ShiftDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AvailableShiftsActivity : AppCompatActivity() {

   private val viewModel: AvailableShiftsViewModel by viewModel()

   private lateinit var binding: AvailableShiftsActivityBinding
   private lateinit var shiftsAdapter: ShiftsAdapter

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      binding = AvailableShiftsActivityBinding.inflate(layoutInflater, findViewById(android.R.id.content), false)
      setContentView(binding.root)

      shiftsAdapter = ShiftsAdapter {
         startActivity(ShiftDetailsActivity.newIntent(this, it))
      }

      binding.pagingLoadStateItem.retryButton.setOnClickListener { shiftsAdapter.retry() }
      val loadStateAdapter = PagingLoadStateAdapter { shiftsAdapter.retry() }

      shiftsAdapter.addLoadStateListener {
         binding.pagingLoadStateItem.apply {
            progressBar.isVisible = it.refresh is LoadState.Loading
            retryButton.isVisible = it.refresh is LoadState.Error
         }
      }

      binding.recyclerView.apply {
         layoutManager = LinearLayoutManager(context)
         adapter = shiftsAdapter.withLoadStateFooter(
            footer = loadStateAdapter
         )
      }

      viewModel.pagedAvailableShifts.observe(this, {
         shiftsAdapter.submitData(lifecycle, it)
      })

      if (savedInstanceState == null) {
         viewModel.getPagedAvailableShifts()
      }
   }
}
