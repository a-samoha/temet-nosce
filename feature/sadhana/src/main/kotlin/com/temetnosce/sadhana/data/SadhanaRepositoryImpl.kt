package com.temetnosce.sadhana.data

import com.temetnosce.sadhana.domain.SadhanaDataSource
import com.temetnosce.sadhana.domain.model.DailyModel
import com.temetnosce.sadhana.domain.repository.SadhanaRepository
import kotlinx.coroutines.delay

internal class SadhanaRepositoryImpl(
    private val sadhanaLocalDataSource: SadhanaDataSource.Local,
    private val sadhanaRemoteDataSource: SadhanaDataSource.Remote,
) : SadhanaRepository {

    override suspend fun insert(value: DailyModel): Result<Unit> =
        sadhanaLocalDataSource.updateSadhana(value)

    override suspend fun queryByDate(): Result<DailyModel> {
        delay(1500)
        return Result.success(DailyModel.EMPTY)
    }
}
