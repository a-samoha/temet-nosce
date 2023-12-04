package com.artsam.sadhana.domain

import com.artsam.sadhana.data.local.SadhanaEntity
import com.artsam.sadhana.domain.model.DailyModel

sealed interface SadhanaDataSource {

    interface Local : SadhanaDataSource {
        suspend fun updateSadhana(value: DailyModel): Result<Unit>
        fun querySadhana(start: Long, end: Long): List<SadhanaEntity>
    }

    interface Remote : SadhanaDataSource
}
