package com.temetnosce.sadhana.domain.model

import com.temetnosce.sadhana.R
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

    fun toSadhanaItemsList(): List<SadhanaItemModel> = listOf(
        SadhanaItemModel(
            SadhanaItemId.MORNING_RISE,
            R.string.sadhana_morning_rise,
            R.drawable.sadhana_ic_sun,
            value = awake
        ),
        SadhanaItemModel(
            SadhanaItemId.KRSHNA_SERVICE,
            R.string.sadhana_service,
            R.drawable.sadhana_ic_feet,
            value = service
        ),
        SadhanaItemModel(
            SadhanaItemId.KIRTAN,
            R.string.sadhana_kirtan,
            R.drawable.sadhana_ic_musicalnote,
            value = kirtan
        ),
        SadhanaItemModel(
            SadhanaItemId.BOOKS_MIN,
            R.string.sadhana_books_min,
            R.drawable.sadhana_ic_bookopen,
            value = books
        ),
        SadhanaItemModel(
            SadhanaItemId.LECTURES,
            R.string.sadhana_lectures,
            R.drawable.sadhana_ic_headphones1,
            value = lectures
        ),
        SadhanaItemModel(
            SadhanaItemId.LIGHTS_OUT,
            R.string.sadhana_lights_out,
            R.drawable.sadhana_ic_moon,
            value = sleep
        ),
        SadhanaItemModel(
            SadhanaItemId.JAPA_07,
            R.string.sadhana_japa,
            null,
            R.string.sadhana_time_0730,
            value = japa7
        ),
        SadhanaItemModel(
            SadhanaItemId.JAPA_10,
            null,
            null,
            R.string.sadhana_time_1000,
            value = japa10
        ),
        SadhanaItemModel(
            SadhanaItemId.JAPA_18,
            null,
            null,
            R.string.sadhana_time_1800,
            value = japa18
        ),
        SadhanaItemModel(
            SadhanaItemId.JAPA_24,
            null,
            null,
            R.string.sadhana_time_2400,
            value = japa24
        ),
    )

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
            japa10 = 0,
            japa18 = 0,
            japa24 = 0,
        )
    }
}
