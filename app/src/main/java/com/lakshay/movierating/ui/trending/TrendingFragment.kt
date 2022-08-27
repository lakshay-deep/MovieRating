package com.lakshay.movierating.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lakshay.movierating.data.model.RestClientResult
import com.lakshay.movierating.databinding.FragmentTrendingBinding
import com.lakshay.movierating.ui.SpaceItemDecoration
import com.lakshay.movierating.ui.detail.GenericDetailActivity
import com.lakshay.movierating.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingFragment: Fragment() {
    private var _binding: FragmentTrendingBinding? = null

    private val binding: FragmentTrendingBinding
        get() = _binding as FragmentTrendingBinding

    private val viewModel: TrendingViewModel by viewModels()
    private var adapter: TrendingAdapter? = null
    private val spaceItemDecoration =  SpaceItemDecoration(6,6)

    companion object{
        fun newInstance(): TrendingFragment {
            val fragment = TrendingFragment()
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
        _binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeLiveData()
        getData()
    }

    private fun setupUI(){
        adapter = TrendingAdapter { trending ->
            startCommonActivity(trending.id)
        }
        binding.rvPopular.layoutManager = GridLayoutManager(context, 2)
        binding.rvPopular.addItemDecoration(spaceItemDecoration)
        binding.rvPopular.adapter = adapter
    }

    private fun observeLiveData(){
        viewModel.trendingLiveData.observe(viewLifecycleOwner){
            when(it.status){
                RestClientResult.Status.LOADING ->{
                    binding.progressBar.isVisible = true
                    binding.rvPopular.isVisible = false
                }
                RestClientResult.Status.SUCCESS ->{
                    binding.progressBar.isVisible = false
                    binding.rvPopular.isVisible = true
                    adapter?.submitList(it.data?.results)
                }
                RestClientResult.Status.ERROR ->{
                    binding.rvPopular.isVisible = false
                    binding.progressBar.isVisible = false
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getData(){
        viewModel.fetchTrending(Constants.All, Constants.Day)
    }

    private fun startCommonActivity(trendingId : Int){
        startActivity(GenericDetailActivity.newIntent(requireContext(), trendingId))
    }
}