package com.shiftkey.codingchallenge.lib.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Facility(
   @SerializedName("id") val id: Long,
   @SerializedName("name") val name: String,
) : Parcelable
