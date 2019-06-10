package com.oldsenior.ella.core_base_impl.room

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = if (value == null) null else Date(value)


    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}
