package com.shiftkey.codingchallenge.feature.shiftdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.databinding.ShiftDetailsActivityBinding
import com.shiftkey.codingchallenge.lib.domain.Shift
import org.threeten.bp.format.DateTimeFormatter

private const val SHIFT_EXTRAS_KEY = "SHIFT_EXTRAS_KEY"

class ShiftDetailsActivity : AppCompatActivity() {

   private val shift by lazy { intent.getParcelableExtra<Shift>(SHIFT_EXTRAS_KEY) as Shift }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      val binding = ShiftDetailsActivityBinding.inflate(layoutInflater, findViewById(android.R.id.content), false)
      setContentView(binding.root)

      binding.apply {
         specialty.text = shift.specialty.name
         facility.text = shift.facility.name
         startTime.text = shift.startTime.format(DateTimeFormatter.RFC_1123_DATE_TIME)
         endTime.text = shift.endTime.format(DateTimeFormatter.RFC_1123_DATE_TIME)
         genericDescription.text = getString(
            R.string.generic_shift_description,
            shift.facility.name,
            shift.specialty.name,
            shift.skill.name
         )
      }
   }

   companion object {
      fun newIntent(context: Context, shift: Shift): Intent {
         return Intent(context, ShiftDetailsActivity::class.java).apply {
            putExtra(SHIFT_EXTRAS_KEY, shift)
         }
      }
   }
}
