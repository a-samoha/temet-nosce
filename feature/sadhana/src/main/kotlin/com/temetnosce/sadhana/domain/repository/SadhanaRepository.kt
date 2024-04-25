package com.temetnosce.sadhana.domain.repository

import com.temetnosce.sadhana.domain.model.DailyModel

interface SadhanaRepository {

    suspend fun saveToDb(value: DailyModel): Result<Unit>
}