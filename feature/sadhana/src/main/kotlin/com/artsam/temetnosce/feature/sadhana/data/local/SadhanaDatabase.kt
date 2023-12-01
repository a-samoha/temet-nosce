package com.artsam.temetnosce.feature.sadhana.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.artsam.temetnosce.feature.sadhana.data.local.SadhanaDatabase.Companion.DB_VERSION
import com.artsam.temetnosce.feature.sadhana.data.local.converter.DateConverter

@Database(
    version = DB_VERSION,
    exportSchema = true,
    entities = [
        SadhanaEntity::class,
    ],
)
@TypeConverters(DateConverter::class)
internal abstract class SadhanaDatabase : RoomDatabase() {
    abstract fun coinDao(): SadhanaDao

    companion object {
        const val DB_NAME = "sadhana_v1_db"
        const val DB_VERSION = 1
    }
}
