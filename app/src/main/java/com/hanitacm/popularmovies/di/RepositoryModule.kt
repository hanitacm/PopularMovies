package com.hanitacm.popularmovies.di

import MoviesRepositoryImpl
import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(moviesApi: MoviesApi): MoviesRepository =
        MoviesRepositoryImpl(moviesApi)

}

