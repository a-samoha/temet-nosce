package com.artsam.temetnosce.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SadhanaItemModel(
    val id: String,
    val label: String,
    @DrawableRes val iconResId: Int?,
    @StringRes val timeResId: Int?,
)
