package com.lakshay.movierating.ui.trending

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lakshay.movierating.data.model.Trending
import com.lakshay.movierating.databinding.CellGenericBinding
import com.lakshay.movierating.util.Constants

class TrendingViewHolder(
    private val binding: CellGenericBinding,
    private val onTrendingClick: (trending: Trending) -> Unit
):
RecyclerView.ViewHolder(binding.root){

    private var trending : Trending? = null

    init {
        binding.root.setOnClickListener{
            trending?.let(onTrendingClick)
        }
    }


    fun setTrending(trending: Trending){
        this.trending = trending
        Glide.with(itemView)
            .load(Constants.IMAGE_BASE_URL.plus(trending.poster_path))
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
            .into(binding.ivImage)
    }
}