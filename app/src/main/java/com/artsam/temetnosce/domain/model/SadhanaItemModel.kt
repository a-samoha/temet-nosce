package com.artsam.temetnosce.domain.model

import androidx.annotation.DrawableRes

data class SadhanaItemModel(
    val id: String,
    val label: String,
    @DrawableRes val iconResId: Int,
)
