package com.hanitacm.popularmovies.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.usecase.GetPopularMoviesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainViewModel @ViewModelInject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _movies = MutableLiveData<List<MovieDomainModel>>()
    val movies: LiveData<List<MovieDomainModel>>
        get() = _movies


    fun getPopularMovies() {
        subscription.add(
            getPopularMoviesUseCase.getPopularMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result -> _movies.postValue(result) }, { error -> showError(error) })
        )
    }

    private fun showError(error: Throwable?) {

    }

    override fun onCleared() {
        subscription.dispose()
        super.onCleared()
    }
}