package com.artsam.sadhana.data.local.converter

import androidx.room.TypeConverter
import java.util.*

internal class DateConverter {

    @TypeConverter
    fun dateToLong(date: Date): Long = date.time

    @TypeConverter
    fun longToDate(ts: Long): Date = Date(ts)
}
