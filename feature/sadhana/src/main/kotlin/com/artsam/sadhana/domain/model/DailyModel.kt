package com.artsam.sadhana.domain.model

import java.util.Calendar
import java.util.Date

data class DailyModel(
    val id: Long,
    val date: Date,
    val awake: String,
    val service: Boolean,
    val kirtan: Boolean,
    val books: Short,
    val lectures: Boolean,
    val sleep: String,
    val japa73: Short,
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
            kirtan = false,
            books = 10,
            lectures = false,
            sleep = "",
            japa73 = 1,
            japa10 = 2,
            japa18 = 3,
            japa24 = 4,
        )
    }
}
