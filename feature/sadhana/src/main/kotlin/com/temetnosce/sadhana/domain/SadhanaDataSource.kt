package com.temetnosce.sadhana.domain

import com.temetnosce.sadhana.data.local.SadhanaEntity
import com.temetnosce.sadhana.domain.model.DailyModel

sealed interface SadhanaDataSource {

    interface Local : SadhanaDataSource {

        suspend fun updateSadhana(value: DailyModel): Result<Unit>

        suspend fun querySadhana(): SadhanaEntity
    }

    interface Remote : SadhanaDataSource
}
