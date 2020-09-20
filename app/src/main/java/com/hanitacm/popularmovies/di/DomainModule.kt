package com.hanitacm.popularmovies.di

import com.hanitacm.domain.repository.MoviesRepository
import com.hanitacm.domain.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(ApplicationComponent::class)
class DomainModule {

    @Provides
    fun provideGetPopularMoviesUseCase(moviesRepository: MoviesRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(moviesRepository)
}