package com.temetnosce.sadhana.domain.usecase

import com.temetnosce.sadhana.domain.model.DailyModel

interface InsertDailySadhanaUseCase {

    suspend operator fun invoke(value: DailyModel): Result<Unit>
}