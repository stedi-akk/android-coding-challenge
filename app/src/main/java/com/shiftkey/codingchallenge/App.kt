package com.shiftkey.codingchallenge

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

   override fun onCreate() {
      super.onCreate()
      AndroidThreeTen.init(this)
      RxJavaPlugins.setErrorHandler { if (it !is UndeliverableException) throw it }
      startKoin {
         androidLogger()
         androidContext(this@App)
         modules(
            listOf(
               com.shiftkey.codingchallenge.feature.availableshifts.koinModule,
               com.shiftkey.codingchallenge.lib.network.koinModule,
               com.shiftkey.codingchallenge.lib.api.koinModule
            )
         )
      }
   }
}
