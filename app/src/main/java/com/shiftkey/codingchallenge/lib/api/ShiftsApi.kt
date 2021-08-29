package com.shiftkey.codingchallenge.lib.api

import com.shiftkey.codingchallenge.lib.domain.Shift
import io.reactivex.Single
import org.threeten.bp.LocalDate
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ShiftsApi {

   @Headers(
      "Content-Type: application/json",
      "Accept: application/json"
   )
   @GET("/api/v2/available_shifts")
   fun getAvailableShifts(
      @Query("start") start: LocalDate?,
      @Query("type") type: AvailableShiftsType?,
      @Query("address") address: String?,
      @Query("radius") radius: Int?
   ): Single<AvailableShiftsResponse>

   enum class AvailableShiftsType(private val apiKey: String) {
      WEEK("week"), FOUR_DAY("4day");

      override fun toString() = apiKey
   }

   data class AvailableShiftsResponse(val data: List<DateShifts>)

   data class DateShifts(val date: LocalDate, val shifts: List<Shift>)

   companion object {
      const val BASE_URL = "https://staging-app.shiftkey.com"
   }
}
