package com.artsam.temetnosce.feature.sadhana.domain

import com.artsam.temetnosce.feature.sadhana.data.local.SadhanaEntity

sealed interface SadhanaDataSource {

    interface Local : SadhanaDataSource {
        fun updateSadhana()
        fun querySadhana(start: Long, end: Long): List<SadhanaEntity>
    }

    interface Remote : SadhanaDataSource
}
