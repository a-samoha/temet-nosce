package com.artsam.sadhana.domain.repository

import com.artsam.sadhana.domain.model.DailyModel

interface SadhanaRepository {

    suspend fun saveToDb(value: DailyModel): Result<Unit>
}