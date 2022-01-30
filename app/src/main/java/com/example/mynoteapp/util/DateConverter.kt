package com.example.mynoteapp.util

import androidx.room.TypeConverter
import java.util.*
import javax.inject.Inject


class DateConverter @Inject constructor(){
    @TypeConverter
  fun   timestampFromDate(date: Date):Long{

return date.time
  }
    @TypeConverter
    fun   dateFromTimestamp(timestamp: Long):Date?{
        return Date(timestamp)
    }
}