package com.shiftkey.codingchallenge.feature.availableshifts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.databinding.ShiftItemBinding
import com.shiftkey.codingchallenge.lib.domain.Shift
import org.threeten.bp.format.DateTimeFormatter

class ShiftsAdapter(
   private val onShiftClicked: (shift: Shift) -> Unit
) : PagingDataAdapter<Shift, ShiftsAdapter.ViewHolder>(ShiftComparator) {

   class ViewHolder(val binding: ShiftItemBinding) : RecyclerView.ViewHolder(binding.root)

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(ShiftItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      getItem(position)?.let { shift ->
         holder.binding.apply {
            specialty.text = shift.specialty.name
            facility.text = shift.facility.name
            startTime.text = shift.startTime.format(DateTimeFormatter.RFC_1123_DATE_TIME)
            endTime.text = shift.endTime.format(DateTimeFormatter.RFC_1123_DATE_TIME)
            root.setOnClickListener { onShiftClicked.invoke(shift) }
         }
      }
   }

   private object ShiftComparator : DiffUtil.ItemCallback<Shift>() {
      override fun areItemsTheSame(oldItem: Shift, newItem: Shift) = oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: Shift, newItem: Shift) = oldItem == newItem
   }
}
