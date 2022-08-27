package com.lakshay.movierating.ui.movie

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lakshay.movierating.data.model.Movie
import com.lakshay.movierating.databinding.CellGenericBinding
import com.lakshay.movierating.util.Constants

class MovieViewHolder(
    private val binding: CellGenericBinding,
    private val onMovieClick: (movie: Movie) -> Unit
): RecyclerView.ViewHolder(binding.root){

    private var movie : Movie? = null

    init {
        binding.root.setOnClickListener{
            movie?.let(onMovieClick)
        }
    }

    fun setMovie(movie: Movie){
        this.movie = movie
        Glide.with(itemView)
            .load(Constants.IMAGE_BASE_URL.plus(movie.poster_path))
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
            .into(binding.ivImage)
    }
}