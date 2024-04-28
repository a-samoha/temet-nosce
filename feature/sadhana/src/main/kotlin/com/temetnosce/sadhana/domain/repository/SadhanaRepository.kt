package com.temetnosce.sadhana.domain.repository

import com.temetnosce.sadhana.domain.model.DailyModel

interface SadhanaRepository {

    suspend fun insert(value: DailyModel): Result<Unit>

    suspend fun queryByDate(): Result<DailyModel> = Result.success(DailyModel.EMPTY)
}