package com.temetnosce.sadhana.di

import androidx.room.Room
import com.temetnosce.sadhana.data.SadhanaRepositoryImpl
import com.temetnosce.sadhana.data.local.SadhanaDatabase
import com.temetnosce.sadhana.data.local.SadhanaLocalDataSource
import com.temetnosce.sadhana.data.remote.SadhanaRemoteDataSource
import com.temetnosce.sadhana.domain.SadhanaDataSource
import com.temetnosce.sadhana.domain.repository.SadhanaRepository
import com.temetnosce.sadhana.domain.usecase.DailySadhanaUpdateUseCase
import com.temetnosce.sadhana.domain.usecase.DailySadhanaUpdateUseCaseImpl
import com.temetnosce.sadhana.presentation.screen.daily.DailyViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sadhanaModule = module {

    viewModelOf(::DailyViewModel)

    factoryOf(::DailySadhanaUpdateUseCaseImpl) bind DailySadhanaUpdateUseCase::class

    factoryOf(::SadhanaRepositoryImpl) bind SadhanaRepository::class

    factoryOf(::SadhanaLocalDataSource) bind SadhanaDataSource.Local::class
    factoryOf(::SadhanaRemoteDataSource) bind SadhanaDataSource.Remote::class

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
