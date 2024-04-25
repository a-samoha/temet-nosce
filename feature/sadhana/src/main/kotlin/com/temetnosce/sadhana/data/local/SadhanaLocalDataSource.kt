package com.temetnosce.sadhana.data.local

import com.temetnosce.sadhana.domain.SadhanaDataSource
import com.temetnosce.sadhana.domain.model.DailyModel

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
