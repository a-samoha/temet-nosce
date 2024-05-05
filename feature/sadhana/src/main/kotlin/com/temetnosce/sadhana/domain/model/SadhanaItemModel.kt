package com.temetnosce.sadhana.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class SadhanaItemModel(
    val id: SadhanaItemId,
    val label: String,
    @DrawableRes val iconResId: Int? = null,
    @StringRes val timeLabelResId: Int? = null,
    val value: Any,
)

enum class SadhanaItemId {
    MORNING_RISE,
    KRSHNA_SERVICE,
    KIRTAN,
    BOOKS_MIN,
    LECTURES,
    LIGHTS_OUT,
    JAPA_07,
    JAPA_10,
    JAPA_18,
    JAPA_24,
}