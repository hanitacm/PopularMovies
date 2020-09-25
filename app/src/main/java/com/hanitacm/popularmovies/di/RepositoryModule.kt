package com.hanitacm.popularmovies.di

import android.content.Context
import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.datasource.cache.MoviesCache
import com.hanitacm.data.datasource.db.MoviesDatabase
import com.hanitacm.data.repository.MovieDataModelMapper
import com.hanitacm.data.repository.MoviesRepositoryImpl
import com.hanitacm.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(
        moviesApi: MoviesApi,
        moviesDataModelMapper: MovieDataModelMapper,
        moviesCache: MoviesCache
    ): MoviesRepository =
        MoviesRepositoryImpl(moviesApi, moviesCache, moviesDataModelMapper)

    @Singleton
    @Provides
    fun provideMoviesDb(@ApplicationContext appContext: Context): MoviesDatabase =
        MoviesDatabase.getDb(appContext)

}

