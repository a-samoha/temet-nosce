package com.artsam.sadhana.data

import com.artsam.sadhana.domain.SadhanaDataSource
import com.artsam.sadhana.domain.model.DailyModel
import com.artsam.sadhana.domain.repository.SadhanaRepository

internal class SadhanaRepositoryImpl(
    private val sadhanaLocalDataSource: SadhanaDataSource.Local,
    private val sadhanaRemoteDataSource: SadhanaDataSource.Remote,
) : SadhanaRepository {

    override suspend fun saveToDb(value: DailyModel): Result<Unit> =
        sadhanaLocalDataSource.updateSadhana(value)
}
