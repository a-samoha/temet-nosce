package com.artsam.sadhana.data

import com.artsam.sadhana.domain.SadhanaDataSource

internal class SadhanaRepositoryImpl(

    private val sadhanaLocalDataSource: SadhanaDataSource.Local,

    private val sadhanaRemoteDataSource: SadhanaDataSource.Remote,
)
