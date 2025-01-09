package dev.garlicbread.fetchhiring.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.garlicbread.fetchhiring.repository.FetchHiringRepository
import dev.garlicbread.fetchhiring.repository.FetchHiringRepositoryReal

@Module
@InstallIn(SingletonComponent::class)
interface FetchHiringRepositoryModule {
    @Binds
    fun bindFetchHiringRepository(
        fetchHiringRepositoryReal: FetchHiringRepositoryReal
    ): FetchHiringRepository
}