package com.artsam.temetnosce.feature.sadhana.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SadhanaItemModel(
    val id: String,
    val label: String,
    @DrawableRes val iconResId: Int? = null,
    @StringRes val timeResId: Int? = null,
    @StringRes val placeholder: Int? = null,
)
