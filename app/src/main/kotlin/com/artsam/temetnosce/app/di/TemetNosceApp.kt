package com.artsam.temetnosce.app.di

import android.app.Application
import com.artsam.sadhana.di.sadhanaModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TemetNosceApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() = startKoin {
        androidContext(this@TemetNosceApp)
        modules(
            sadhanaModule,
        )
    }
}