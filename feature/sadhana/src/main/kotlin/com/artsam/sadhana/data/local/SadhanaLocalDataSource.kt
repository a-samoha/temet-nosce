package com.artsam.sadhana.data.local

import com.artsam.sadhana.domain.SadhanaDataSource
import com.artsam.sadhana.domain.model.DailyModel

internal class SadhanaLocalDataSource(
    private val dao: SadhanaDao,
) : SadhanaDataSource.Local {

    override fun querySadhana(start: Long, end: Long): List<SadhanaEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSadhana(value: DailyModel): Result<Unit> = runCatching {
        dao.insert(SadhanaEntity.mapFromDomain(value))
    }
}
