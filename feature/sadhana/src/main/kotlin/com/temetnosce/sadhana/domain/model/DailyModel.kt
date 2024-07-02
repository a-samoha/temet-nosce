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
            value = awake,
        ),
        SadhanaItemModel(
            SadhanaItemId.KRSHNA_SERVICE,
            R.string.sadhana_service,
            R.drawable.sadhana_ic_feet,
            value = service,
        ),
        SadhanaItemModel(
            SadhanaItemId.KIRTAN,
            R.string.sadhana_kirtan,
            R.drawable.sadhana_ic_musicalnote,
            value = kirtan,
        ),
        SadhanaItemModel(
            SadhanaItemId.BOOKS_MIN,
            R.string.sadhana_books_min,
            R.drawable.sadhana_ic_bookopen,
            value = if (books == 0.toShort()) "" else books,
        ),
        SadhanaItemModel(
            SadhanaItemId.LECTURES,
            R.string.sadhana_lectures,
            R.drawable.sadhana_ic_headphones1,
            value = lectures,
        ),
        SadhanaItemModel(
            SadhanaItemId.LIGHTS_OUT,
            R.string.sadhana_lights_out,
            R.drawable.sadhana_ic_moon,
            value = sleep,
        ),
        SadhanaItemModel(
            SadhanaItemId.JAPA_07,
            R.string.sadhana_japa,
            null,
            R.string.sadhana_time_07_30,
            value = if (japa7 == 0.toShort()) "" else japa7,
        ),
        SadhanaItemModel(
            SadhanaItemId.JAPA_10,
            null,
            null,
            R.string.sadhana_time_10_00,
            value = if (japa10 == 0.toShort()) "" else japa10,
        ),
        SadhanaItemModel(
            SadhanaItemId.JAPA_18,
            null,
            null,
            R.string.sadhana_time_18_00,
            value = if (japa18 == 0.toShort()) "" else japa18,
        ),
        SadhanaItemModel(
            SadhanaItemId.JAPA_24,
            null,
            null,
            R.string.sadhana_time_24_00,
            value = if (japa24 == 0.toShort()) "" else japa24,
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

fun List<SadhanaItemModel>.toDailyModel(): DailyModel {
    var dailyModel = DailyModel.EMPTY
        .copy(date = Date())
    this.forEach { sadhanaItem ->
        dailyModel = when (sadhanaItem.id) {
            SadhanaItemId.MORNING_RISE -> dailyModel.copy(awake = "${sadhanaItem.value}")
            SadhanaItemId.KRSHNA_SERVICE -> dailyModel.copy(service = sadhanaItem.value as Boolean)
            SadhanaItemId.KIRTAN -> dailyModel.copy(kirtan = sadhanaItem.value as Boolean)
            SadhanaItemId.BOOKS_MIN -> dailyModel.copy(
                books = sadhanaItem.value.toString()
                    .let { if (it.isNotBlank()) it.toShort() else 0 })
            SadhanaItemId.LECTURES -> dailyModel.copy(lectures = sadhanaItem.value as Boolean)
            SadhanaItemId.LIGHTS_OUT -> dailyModel.copy(sleep = "${sadhanaItem.value}")
            SadhanaItemId.JAPA_07 -> dailyModel.copy(
                japa7 = sadhanaItem.value.toString()
                    .let { if (it.isNotBlank()) it.toShort() else 0 })
            SadhanaItemId.JAPA_10 -> dailyModel.copy(
                japa10 = sadhanaItem.value.toString()
                    .let { if (it.isNotBlank()) it.toShort() else 0 })
            SadhanaItemId.JAPA_18 -> dailyModel.copy(
                japa18 = sadhanaItem.value.toString()
                    .let { if (it.isNotBlank()) it.toShort() else 0 })
            SadhanaItemId.JAPA_24 -> dailyModel.copy(
                japa24 = sadhanaItem.value.toString()
                    .let { if (it.isNotBlank()) it.toShort() else 0 })
        }
    }
    return dailyModel
}