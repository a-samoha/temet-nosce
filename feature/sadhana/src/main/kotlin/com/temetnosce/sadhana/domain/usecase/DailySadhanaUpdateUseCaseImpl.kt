package com.temetnosce.sadhana.domain.usecase

import com.temetnosce.sadhana.domain.model.DailyModel
import com.temetnosce.sadhana.domain.repository.SadhanaRepository

class DailySadhanaUpdateUseCaseImpl(
    private val repo: SadhanaRepository
) : DailySadhanaUpdateUseCase {

    override suspend fun invoke(value: DailyModel): Result<Unit> =
        repo.saveToDb(value)
}