package com.temetnosce.sadhana.domain.usecase

import com.temetnosce.sadhana.domain.model.DailyModel

interface DailySadhanaUpdateUseCase {

    suspend operator fun invoke(value: DailyModel): Result<Unit>
}