package com.hanitacm.popularmovies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanitacm.domain.UseCaseResult
import com.hanitacm.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _viewState = MutableLiveData<MainViewModelState>()
    val viewState: LiveData<MainViewModelState>
        get() {
            if (_viewState.value == null) loading()
            return _viewState
        }

    fun getPopularMovies() {
        subscription.add(
            getPopularMoviesUseCase.getPopularMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result ->
                    when (result) {
                        is UseCaseResult.Success -> _viewState.postValue(
                            MainViewModelState.MoviesLoaded(
                                result.data
                            )
                        )
                        is UseCaseResult.Error<*> -> _viewState.postValue(
                            MainViewModelState.MoviesLoadFailure(
                                result.error
                            )
                        )
                    }
                })
    }

    private fun loading() {
        _viewState.value = MainViewModelState.Loading
    }

    override fun onCleared() {
        subscription.dispose()
        super.onCleared()
    }
}
