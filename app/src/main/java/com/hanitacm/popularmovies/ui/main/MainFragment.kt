package com.hanitacm.popularmovies.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.popularmovies.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {


    private val viewModel: MainViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()

        viewModel.getPopularMovies()
    }

    private fun subscribeObservers() {
        viewModel.movies.observe(
            viewLifecycleOwner,
            Observer { result -> appendBlogTitles(result) })
    }

    private fun appendBlogTitles(movies: List<MovieDomainModel>) {
        val sb = StringBuilder()
        for (movie in movies) {
            sb.append(movie.title + "\n")
        }
        message.text = sb.toString()
    }
}