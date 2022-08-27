package com.lakshay.movierating.ui.profile.fav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lakshay.movierating.databinding.FragmentFavBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment: Fragment() {
    private var _binding: FragmentFavBinding? = null

    private val binding: FragmentFavBinding
        get() = _binding as FragmentFavBinding

    companion object{
        fun newInstance(): FavFragment {
            val fragment = FavFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }
}