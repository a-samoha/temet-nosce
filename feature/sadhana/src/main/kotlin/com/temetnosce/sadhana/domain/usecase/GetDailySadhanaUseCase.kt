package com.temetnosce.sadhana.domain.usecase

import com.temetnosce.sadhana.domain.model.DailyModel

interface GetDailySadhanaUseCase {
    suspend operator fun invoke(): Result<DailyModel>
}