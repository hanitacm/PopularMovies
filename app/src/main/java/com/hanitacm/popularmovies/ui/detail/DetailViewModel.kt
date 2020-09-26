package com.hanitacm.popularmovies.ui.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hanitacm.domain.UseCaseResult
import com.hanitacm.domain.usecase.GetMovieDetailUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel @ViewModelInject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _viewState = MutableLiveData<DetailViewModelState>()
    val viewState: LiveData<DetailViewModelState>
        get() {
            if (_viewState.value == null) loading()
            return _viewState
        }

    fun getMovieDetail(id: Int) {
        subscription.add(
            getMovieDetailUseCase.getMovieDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result ->
                    when (result) {
                        is UseCaseResult.Success -> _viewState.postValue(
                            DetailViewModelState.DetailLoaded(result.data)
                        )
                        is UseCaseResult.Error<*> -> _viewState.postValue(
                            DetailViewModelState.DetailLoadFailure(
                                result.error
                            )
                        )


                    }
                })
    }

    private fun loading() {
        _viewState.value = DetailViewModelState.Loading
    }

    override fun onCleared() {
        subscription.dispose()
        super.onCleared()
    }
}