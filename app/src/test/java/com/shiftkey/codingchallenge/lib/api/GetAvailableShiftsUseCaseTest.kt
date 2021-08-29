package com.shiftkey.codingchallenge.lib.api

import com.shiftkey.codingchallenge.lib.domain.Facility
import com.shiftkey.codingchallenge.lib.domain.Shift
import com.shiftkey.codingchallenge.lib.domain.Skill
import com.shiftkey.codingchallenge.lib.domain.Specialty
import com.shiftkey.codingchallenge.lib.network.RetrofitFactory
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit

class GetAvailableShiftsUseCaseTest {

   @Test
   fun test() {
      val fakeStart = LocalDate.now()
      val fakeStartEndTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault())
      val fakeResponse = ShiftsApi.AvailableShiftsResponse(
         data = listOf(
            ShiftsApi.DateShifts(
               date = LocalDate.of(1990, 12, 18),
               shifts = listOf(
                  Shift(
                     id = 123,
                     startTime = fakeStartEndTime,
                     endTime = fakeStartEndTime,
                     facility = Facility(
                        id = 456,
                        name = "123"
                     ),
                     skill = Skill(
                        id = 789,
                        name = "456"
                     ),
                     specialty = Specialty(
                        id = 69,
                        name = "789"
                     )
                  )
               )
            ),
            ShiftsApi.DateShifts(
               date = fakeStart,
               shifts = listOf(
                  Shift(
                     id = 666,
                     startTime = fakeStartEndTime,
                     endTime = fakeStartEndTime,
                     facility = Facility(
                        id = 999,
                        name = "Test facility"
                     ),
                     skill = Skill(
                        id = 333,
                        name = "Ability to invent skills"
                     ),
                     specialty = Specialty(
                        id = 69,
                        name = "Skills inventor"
                     )
                  )
               )
            )
         )
      )

      val api = mockk<ShiftsApi>()
      every { api.getAvailableShifts(any(), any(), any(), any()) } returns Single.just(fakeResponse)

      val retrofit = mockk<Retrofit>()
      every { retrofit.create(ShiftsApi::class.java) } returns api

      val retrofitFactory = mockk<RetrofitFactory>()
      every { retrofitFactory.create(any()) } returns retrofit

      val result = GetAvailableShiftsUseCase(retrofitFactory)
         .get(0)
         .test()
         .values()
         .first()

      result.apply {
         assertTrue(size == 1)
         get(0).apply {
            assertTrue(startTime == fakeStartEndTime)
            assertTrue(endTime == fakeStartEndTime)
            assertTrue(facility == Facility(999, "Test facility"))
            assertTrue(skill == Skill(333, "Ability to invent skills"))
            assertTrue(specialty == Specialty(69, "Skills inventor"))
         }
      }
   }
}
