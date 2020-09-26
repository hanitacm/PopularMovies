package com.hanitacm.popularmovies.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.popularmovies.R
import com.hanitacm.popularmovies.ui.model.Movie
import kotlinx.android.synthetic.main.movie_card_item.view.*
import kotlin.properties.Delegates


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var items: List<MovieDomainModel> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_card_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mediaItem = items[position]

        holder.bind(mediaItem)
        holder.itemView.setOnClickListener {
            val navigationController = Navigation.findNavController(it)
            val movie = with(mediaItem) {
                Movie(
                    id = id,
                    title = title,
                    overview,
                    releaseDate,
                    posterPath,
                    backdropPath,
                    originalLanguage,
                    originalTitle,
                    popularity,
                    voteAverage
                )
            }

            navigationController.navigate(
                R.id.action_firstFragment_to_detailFragment,
                bundleOf("movie" to movie)
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView = itemView.photo
        private val title: TextView = itemView.title
        private val rate: TextView = itemView.rate


        fun bind(mediaItem: MovieDomainModel) {
            photo.load("https://image.tmdb.org/t/p/w185${mediaItem.posterPath}")
            title.text = mediaItem.title
            rate.text = mediaItem.voteAverage.toString()


        }
    }


}
