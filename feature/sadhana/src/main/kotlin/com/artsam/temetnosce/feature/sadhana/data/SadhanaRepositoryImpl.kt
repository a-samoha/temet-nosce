package com.artsam.temetnosce.feature.sadhana.data

import com.artsam.temetnosce.feature.sadhana.domain.SadhanaDataSource

internal class SadhanaRepositoryImpl(

    private val sadhanaLocalDataSource: SadhanaDataSource.Local,

    private val sadhanaRemoteDataSource: SadhanaDataSource.Remote,
)