package com.lakshay.movierating.ui.person

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lakshay.movierating.data.model.Person
import com.lakshay.movierating.databinding.CellGenericBinding
import com.lakshay.movierating.util.Constants

class PersonViewHolder(
    private val binding: CellGenericBinding,
    private val onPersonClick: (person: Person) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private var person: Person? = null

    init {
        binding.root.setOnClickListener {
            person?.let(onPersonClick)
        }
    }

    fun setPerson(person: Person) {
        this.person = person
        Glide.with(itemView)
            .load(Constants.IMAGE_BASE_URL.plus(person.profile_path))
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
            .into(binding.ivImage)
    }
}