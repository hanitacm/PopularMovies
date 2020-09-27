package com.hanitacm.popularmovies.di

import com.hanitacm.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import org.mockito.Mockito
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object TestRepositoryModule {
    @Singleton
    @Provides
    fun provideMoviesRepository(): MoviesRepository = Mockito.mock(MoviesRepository::class.java)

}