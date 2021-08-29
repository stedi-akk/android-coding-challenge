package com.shiftkey.codingchallenge.lib.network

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class JsonLocalDateAdapter : TypeAdapter<LocalDate>() {

   override fun write(writer: JsonWriter, value: LocalDate) {
      writer.value(value.toString())
   }

   override fun read(reader: JsonReader): LocalDate {
      return LocalDate.parse(reader.nextString(), DateTimeFormatter.ISO_LOCAL_DATE)
   }
}
