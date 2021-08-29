package com.shiftkey.codingchallenge.lib.network

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class JsonZonedDateTimeTimeAdapter : TypeAdapter<ZonedDateTime>() {

   override fun write(writer: JsonWriter, value: ZonedDateTime) {
      writer.value(value.toString())
   }

   override fun read(reader: JsonReader): ZonedDateTime {
      return ZonedDateTime.parse(reader.nextString(), DateTimeFormatter.ISO_DATE_TIME)
   }
}
