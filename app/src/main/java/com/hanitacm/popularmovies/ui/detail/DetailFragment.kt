package com.hanitacm.popularmovies.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.hanitacm.popularmovies.R
import com.hanitacm.popularmovies.ui.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_fragment.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val viewModel: DetailViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie: Movie = arguments?.get("movie") as Movie


        viewModel.getMovieDetail(movie.id)
        //movieOverview.text = movie.overview
        //movieTitle.text = movie.title
    }
}