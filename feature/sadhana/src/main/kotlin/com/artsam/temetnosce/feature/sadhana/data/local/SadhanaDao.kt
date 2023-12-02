package com.artsam.temetnosce.feature.sadhana.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SadhanaDao {

    @Query("SELECT * FROM ${SadhanaContract.TABLE} WHERE ${SadhanaContract.DATE} BETWEEN :start AND :end")
    suspend fun querySadhanaForPeriod(start: Long, end: Long): List<SadhanaEntity>

    @Insert
    suspend fun insert(item: SadhanaEntity)
}
