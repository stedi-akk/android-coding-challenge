package com.shiftkey.codingchallenge.lib.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory {

   private val defaultClient by lazy {
      OkHttpClient.Builder()
         .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
         .connectTimeout(10, TimeUnit.SECONDS)
         .readTimeout(10, TimeUnit.SECONDS)
         .writeTimeout(10, TimeUnit.SECONDS)
         .build()
   }

   private val defaultGson by lazy {
      GsonBuilder()
         .registerTypeAdapter(LocalDate::class.java, JsonLocalDateAdapter().nullSafe())
         .registerTypeAdapter(ZonedDateTime::class.java, JsonZonedDateTimeTimeAdapter().nullSafe())
         .create()
   }

   fun create(
      baseUrl: String,
      client: OkHttpClient = defaultClient,
      gson: Gson = defaultGson
   ): Retrofit = Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
}
