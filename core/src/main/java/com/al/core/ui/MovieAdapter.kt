package com.al.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.al.core.databinding.MovieItemListBinding
import com.al.core.domain.model.Movies
import com.al.core.R
import com.bumptech.glide.Glide

class MovieAdapter: ListAdapter<Movies, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class MovieViewHolder(private var binding: MovieItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movies) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDate.text = movie.releaseDate
                tvItemRating.text = movie.voteAverage?.toString() ?: "N/A"
                tvItemOverview.text = movie.overview

                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
                    .placeholder(R.drawable.movie_placeholder)
                    .error(R.drawable.movie_placeholder)
                    .into(ivItemPoster)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movies> =
            object : DiffUtil.ItemCallback<Movies>() {
                override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                    return oldItem == newItem
                }
            }
    }
}