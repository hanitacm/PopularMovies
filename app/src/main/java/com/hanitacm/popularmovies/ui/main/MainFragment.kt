package com.hanitacm.popularmovies.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.popularmovies.R
import com.hanitacm.popularmovies.ui.main.adapters.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {


    private val viewModel: MainViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: MoviesAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        subscribeObservers()

        viewModel.getPopularMovies()
    }

    private fun setupRecyclerView() {
        viewManager = GridLayoutManager(requireContext(), 2)
        viewAdapter = MoviesAdapter()
        rv_movies.adapter = viewAdapter
        rv_movies.layoutManager = viewManager
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is MainViewModelState.Loading -> showProgressBar()
                    is MainViewModelState.MoviesLoaded -> loadMovies(it.movies)
                    is MainViewModelState.MoviesLoadFailure -> showError(it.error)

                }
            })
    }


    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun loadMovies(movies: List<MovieDomainModel>) {
        progressBar.visibility = View.GONE
        viewAdapter.items = movies
    }

    private fun showError(error: Exception) {

    }


}