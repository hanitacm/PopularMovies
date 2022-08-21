package com.hanitacm.popularmovies.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.popularmovies.R
import com.hanitacm.popularmovies.databinding.MovieCardItemBinding
import kotlin.properties.Delegates


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var items: List<MovieDomainModel> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mediaItem = items[position]

        holder.bind(mediaItem)
        holder.itemView.setOnClickListener {
            val navigationController = Navigation.findNavController(it)

            navigationController.navigate(
                R.id.action_firstFragment_to_detailFragment, bundleOf("movie" to mediaItem.id)
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: MovieCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mediaItem: MovieDomainModel) {
            binding.photo.load("https://image.tmdb.org/t/p/w185${mediaItem.posterPath}")
            binding.title.text = mediaItem.title
            binding.rate.text = mediaItem.voteAverage.toString()


        }

        companion object {
            fun create(
                parent: ViewGroup,
            ): ViewHolder {
                val binding =
                    MovieCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }

    }


}
