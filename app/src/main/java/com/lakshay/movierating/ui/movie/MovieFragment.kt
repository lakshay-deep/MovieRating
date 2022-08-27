package com.lakshay.movierating.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lakshay.movierating.data.model.Movie
import com.lakshay.movierating.data.model.RestClientResult
import com.lakshay.movierating.databinding.FragmentMovieBinding
import com.lakshay.movierating.ui.SpaceItemDecoration
import com.lakshay.movierating.ui.detail.GenericDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment: Fragment() {

    private var _binding: FragmentMovieBinding? = null

    private val binding: FragmentMovieBinding
    get() = _binding as FragmentMovieBinding

    private val viewModel : MovieViewModel by viewModels()

    private var adapter: MovieAdapter? = null

    private val spaceItemDecoration = SpaceItemDecoration(6, 6)


    companion object {
        fun newInstance(): MovieFragment {
            val fragment = MovieFragment()
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
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeLiveData()
        getData()
    }

    private fun setupUI() {
        adapter = MovieAdapter{ movie ->
            startGenericActivity(movie.id)
        }

        binding.rvPopular.layoutManager = GridLayoutManager(context, 2)
        binding.rvPopular.addItemDecoration(spaceItemDecoration)
        binding.rvPopular.adapter = adapter
    }

    private fun observeLiveData() {
        viewModel.moviesLiveData.observe(
            viewLifecycleOwner) {
            when (it.status){
                RestClientResult.Status.LOADING ->{
                    binding.progressBar.isVisible = true
                    binding.rvPopular.isVisible = false
                }
                RestClientResult.Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    binding.rvPopular.isVisible = true
                    adapter?.submitList(it.data?.results)
                }
                RestClientResult.Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    binding.rvPopular.isVisible = false
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getData() {
        viewModel.fetchPopularMovies()
    }

    private fun startGenericActivity(movieID : Int){
        startActivity(GenericDetailActivity.newIntent(requireContext(), movieID))
    }
}