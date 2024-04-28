package com.temetnosce.sadhana.domain.model

import java.util.Calendar
import java.util.Date

data class DailyModel(
    val id: Long,
    val date: Date,
    val awake: String,
    val service: Boolean,
    val lectures: Boolean,
    val kirtan: Boolean,
    val books: Short,
    val sleep: String,
    val japa7: Short,
    val japa10: Short,
    val japa18: Short,
    val japa24: Short,
) {
    companion object {
        val EMPTY = DailyModel(
            id = 0L,
            date = Calendar.getInstance().time,
            awake = "",
            service = false,
            lectures = false,
            kirtan = false,
            books = 0,
            sleep = "",
            japa7 = 0,
            japa10 =0,
            japa18 = 0,
            japa24 = 0,
        )
    }
}
