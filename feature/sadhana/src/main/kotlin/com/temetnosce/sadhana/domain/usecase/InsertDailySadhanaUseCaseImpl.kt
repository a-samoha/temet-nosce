package com.temetnosce.sadhana.domain.usecase

import com.temetnosce.sadhana.domain.model.DailyModel
import com.temetnosce.sadhana.domain.repository.SadhanaRepository

class InsertDailySadhanaUseCaseImpl(
    private val repo: SadhanaRepository
) : InsertDailySadhanaUseCase {

    override suspend fun invoke(value: DailyModel): Result<Unit> =
        repo.insert(value)
}