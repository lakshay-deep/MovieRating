package com.lakshay.movierating.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.lakshay.movierating.data.model.Tv
import com.lakshay.movierating.databinding.CellGenericBinding

class TvAdapter(
    private val onTvClick : (tv: Tv) -> Unit
) : ListAdapter<Tv, TvViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Tv>(){
            override fun areItemsTheSame(oldItem: Tv, newItem: Tv): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Tv, newItem: Tv): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val binding = CellGenericBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(binding, onTvClick)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        getItem(position)?.let {
            holder.setTv(it)
        }
    }
}