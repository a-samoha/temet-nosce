package com.temetnosce.sadhana.domain.usecase

import com.temetnosce.sadhana.domain.model.DailyModel
import com.temetnosce.sadhana.domain.repository.SadhanaRepository

class GetDailySadhanaUseCaseImpl(
    private val repo: SadhanaRepository
) : GetDailySadhanaUseCase {
    override suspend fun invoke(): Result<DailyModel> = repo.queryByDate()
}