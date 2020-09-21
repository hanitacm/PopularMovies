package com.hanitacm.popularmovies.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.popularmovies.R
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
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView = itemView.photo


        fun bind(mediaItem: MovieDomainModel) {
            photo.load("https://image.tmdb.org/t/p/w185${mediaItem.posterPath}")
        }
    }


}
