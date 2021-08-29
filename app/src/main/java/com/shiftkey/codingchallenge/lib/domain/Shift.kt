package com.shiftkey.codingchallenge.lib.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import org.threeten.bp.ZonedDateTime

@Parcelize
data class Shift(
   @SerializedName("shift_id") val id: Long,
   @SerializedName("start_time") val startTime: ZonedDateTime,
   @SerializedName("end_time") val endTime: ZonedDateTime,
   @SerializedName("facility_type") val facility: Facility,
   @SerializedName("skill") val skill: Skill,
   @SerializedName("localized_specialty") val specialty: Specialty
) : Parcelable
