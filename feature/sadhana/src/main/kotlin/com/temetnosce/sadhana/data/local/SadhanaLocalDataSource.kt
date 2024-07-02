package com.temetnosce.sadhana.data.local

import com.temetnosce.sadhana.domain.SadhanaDataSource
import com.temetnosce.sadhana.domain.model.DailyModel
import kotlinx.datetime.Clock.System.now
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

internal class SadhanaLocalDataSource(
    private val dao: SadhanaDao,
) : SadhanaDataSource.Local {

    override suspend fun querySadhana(): SadhanaEntity {
        val tz = TimeZone.currentSystemDefault()
        val ldt = now().toLocalDateTime(tz).date.atStartOfDayIn(tz)
        val start = ldt.toEpochMilliseconds()
        val end = ldt.plus(1L, DateTimeUnit.DAY, tz).toEpochMilliseconds()
        return dao.querySadhanaForPeriod(start, end).first()
    }

    override suspend fun updateSadhana(value: DailyModel): Result<Unit> = runCatching {
        dao.insert(SadhanaEntity.mapFromDomain(value))
    }
}
