package com.counter.android.di

import com.counter.android.network.service.CounterService
import com.counter.android.persistence.CounterDao
import com.counter.android.repository.CounterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideCounterRepository(
        counterService: CounterService,
        counterDao: CounterDao
    ): CounterRepository {
        return CounterRepository(counterService, counterDao)
    }
}