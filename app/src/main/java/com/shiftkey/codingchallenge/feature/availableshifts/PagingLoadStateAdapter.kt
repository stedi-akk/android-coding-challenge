package com.shiftkey.codingchallenge.feature.availableshifts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.databinding.PagingLoadStateItemBinding

class PagingLoadStateAdapter(
   private val onRetryClicked: () -> Unit
) : LoadStateAdapter<PagingLoadStateAdapter.ViewHolder>() {

   override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
      return ViewHolder(PagingLoadStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
   }

   override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
      holder.binding.apply {
         progressBar.isVisible = loadState is LoadState.Loading
         retryButton.isVisible = loadState is LoadState.Error
         retryButton.setOnClickListener { onRetryClicked() }
      }
   }

   class ViewHolder(val binding: PagingLoadStateItemBinding) : RecyclerView.ViewHolder(binding.root)
}
