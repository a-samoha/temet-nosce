package com.artsam.sadhana.domain.usecase

import com.artsam.sadhana.domain.model.DailyModel

interface DailySadhanaUpdateUseCase {

    suspend operator fun invoke(value: DailyModel): Result<Unit>
}