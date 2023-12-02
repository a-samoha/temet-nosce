package com.artsam.sadhana.domain

import com.artsam.sadhana.data.local.SadhanaEntity

sealed interface SadhanaDataSource {

    interface Local : SadhanaDataSource {
        fun updateSadhana()
        fun querySadhana(start: Long, end: Long): List<SadhanaEntity>
    }

    interface Remote : SadhanaDataSource
}
