package com.lakshay.movierating.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.BlurMaskFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jaeger.library.StatusBarUtil
import com.lakshay.movierating.R
import com.lakshay.movierating.data.model.Movie
import com.lakshay.movierating.data.model.RestClientResult
import com.lakshay.movierating.data.model.Tv
import com.lakshay.movierating.databinding.ActivityGenericDetailBinding
import com.lakshay.movierating.util.BlurTransformation
import com.lakshay.movierating.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenericDetailActivity : AppCompatActivity() {

    private var _binding: ActivityGenericDetailBinding? = null

    private val binding : ActivityGenericDetailBinding
        get() = _binding as ActivityGenericDetailBinding

    private val viewModel : GenericViewModel by viewModels()

    private val tv: Tv? = null
    private val movie: Movie? = null

    private var tvId: Int = 0
    private var movieId: Int = 0
    private var id: Int = 0

    companion object {
        private const val EXTRA_ID = "EXTRA_ID"
        private const val EXTRA_TV_ID = "EXTRA_TV_ID"
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, GenericDetailActivity::class.java)
            intent.putExtra(EXTRA_TV_ID, id)
//            intent.putExtra(EXTRA_TV_ID, id)
//            intent.putExtra(EXTRA_MOVIE_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGenericDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        supportActionBar?.hide()

        StatusBarUtil.setColorNoTranslucent(this, ContextCompat.getColor(this, R.color.colorPrimaryDark))

        getArguments()
        getData()
        observeLiveData()
    }


    private fun observeLiveData(){
        viewModel.tvDetailLiveData.observe(this){
            when(it.status){
                RestClientResult.Status.LOADING ->{
                    binding.progressBar.isVisible = true
                    binding.ivBackdropPath.isVisible = false
                }

                RestClientResult.Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    Glide.with(this)
                        .load(Constants.IMAGE_BASE_URL.plus(it.data?.poster_path))
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding.ivPosterPath)
                    binding.tvGeneres.text = it.data!!.genres[0]!!.name
                    Glide.with(this)
                        .asBitmap()
                        .load(Constants.IMAGE_BASE_URL.plus(it.data?.backdrop_path))
                        .transform(BlurTransformation(this))
                        .into(binding.ivBackdropPath)
//                    binding.ivBackdropPath.isVisible = false

                    binding.tvOverviewDesc.text = it.data?.overview.toString()
                    val userScore = (it.data?.vote_average)!! * 10
                    binding.tvUserScoreDesc.text = userScore.toString() + "%"
                }

                RestClientResult.Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }


        viewModel.movieDetailLivedata.observe(this){
            when(it.status){
                RestClientResult.Status.LOADING ->{
                    binding.progressBar.isVisible = true
                    binding.ivBackdropPath.isVisible = false
                }

                RestClientResult.Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    Glide.with(this)
                        .load(Constants.IMAGE_BASE_URL.plus(it.data?.poster_path))
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding.ivPosterPath)
                    binding.tvGeneres.text = it.data!!.genres[0]!!.name
                    Glide.with(this)
                        .asBitmap()
                        .load(Constants.IMAGE_BASE_URL.plus(it.data?.backdrop_path))
                        .transform(BlurTransformation(this))
                        .into(binding.ivBackdropPath)
//                    binding.ivBackdropPath.isVisible = false

                    binding.tvOverviewDesc.text = it.data?.overview.toString()
                    val userScore = (it.data?.vote_average)!! * 10
                    binding.tvUserScoreDesc.text = userScore.toString() + "%"
                }

                RestClientResult.Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getArguments(){
        intent?.let {
            tvId = it.getIntExtra(EXTRA_TV_ID, 0)
            movieId= it.getIntExtra(EXTRA_MOVIE_ID, 0)
//            id = it.getIntExtra(EXTRA_TV_ID, id)
        }
    }

    private fun getData(){
        viewModel.fetchTvDetailsByID(tvId)
        viewModel.fetchMovieDetailsByID(movieId)
//        viewModel.fetchDetailsByID(id)
    }
}