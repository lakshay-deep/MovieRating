package com.lakshay.movierating.ui.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.lakshay.movierating.data.model.Trending
import com.lakshay.movierating.databinding.CellGenericBinding

class TrendingAdapter(
    private val onTrendingClick : (trending: Trending) -> Unit
): ListAdapter<Trending, TrendingViewHolder>(DIFF_CALLBACK){

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Trending>(){
            override fun areItemsTheSame(oldItem: Trending, newItem: Trending): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Trending, newItem: Trending): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding = CellGenericBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingViewHolder(binding,onTrendingClick)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        getItem(position)?.let {
            holder.setTrending(it)
        }
    }
}