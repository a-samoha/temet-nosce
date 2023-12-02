package com.artsam.temetnosce.feature.sadhana.di

import androidx.room.Room
import com.artsam.temetnosce.feature.sadhana.data.local.SadhanaDatabase
import com.artsam.temetnosce.feature.sadhana.data.local.SadhanaLocalDataSource
import com.artsam.temetnosce.feature.sadhana.domain.SadhanaDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sadhanaModule = module {

    factoryOf(::SadhanaLocalDataSource) bind SadhanaDataSource.Local::class

    // Database
    single {
        Room.databaseBuilder(
            androidApplication(),
            SadhanaDatabase::class.java,
            SadhanaDatabase.DB_NAME,
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<SadhanaDatabase>().sadhanaDao() }
}