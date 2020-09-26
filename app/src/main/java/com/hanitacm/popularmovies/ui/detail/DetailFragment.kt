package com.hanitacm.popularmovies.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import coil.api.load
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.popularmovies.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.detail_fragment.progressBar
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val viewModel: DetailViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is DetailViewModelState.Loading -> showProgressBar()
                is DetailViewModelState.DetailLoaded -> loadMovieDetail(it.movie)
                is DetailViewModelState.DetailLoadFailure -> showError(it.error)
            }
        })
        val movieId: Int = arguments?.get("movie") as Int


        viewModel.getMovieDetail(movieId)

    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun loadMovieDetail(movie: MovieDomainModel) {
        progressBar.visibility = View.GONE

        with(movie) {
            movieOverview.text = overview
            movieTitle.text = title
            country_date.text =  "${originalLanguage.toUpperCase(Locale.getDefault())} | $releaseDate"
            rating.text = voteAverage.toString()
            backdrop.load("https://image.tmdb.org/t/p/w780$backdropPath")
        }
    }

    private fun showError(error: Exception) {

    }


}