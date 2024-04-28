package com.temetnosce.sadhana.data

import com.temetnosce.sadhana.domain.SadhanaDataSource
import com.temetnosce.sadhana.domain.model.DailyModel
import com.temetnosce.sadhana.domain.repository.SadhanaRepository

internal class SadhanaRepositoryImpl(
    private val sadhanaLocalDataSource: SadhanaDataSource.Local,
    private val sadhanaRemoteDataSource: SadhanaDataSource.Remote,
) : SadhanaRepository {

    override suspend fun insert(value: DailyModel): Result<Unit> =
        sadhanaLocalDataSource.updateSadhana(value)
}
