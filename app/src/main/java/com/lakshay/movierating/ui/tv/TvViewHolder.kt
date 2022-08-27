package com.lakshay.movierating.ui.tv

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lakshay.movierating.data.model.Tv
import com.lakshay.movierating.databinding.CellGenericBinding
import com.lakshay.movierating.util.Constants

class TvViewHolder(
    private val binding: CellGenericBinding,
    private val onTvClick: (tv: Tv) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private var tv : Tv? = null

    init {
        binding.root.setOnClickListener{
            tv?.let(onTvClick)
        }
    }

    fun setTv(tv: Tv){
        this.tv = tv
        Glide.with(itemView)
            .load(Constants.IMAGE_BASE_URL.plus(tv.poster_path))
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
            .into(binding.ivImage)
    }
}