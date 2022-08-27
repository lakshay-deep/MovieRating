package com.lakshay.movierating.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lakshay.movierating.data.model.*
import com.lakshay.movierating.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenericViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val tv: Tv? = null
    private val movie: Movie? = null

    private val _tvDetailLiveData = MutableLiveData<RestClientResult<TvDetailResponse>>()
    val tvDetailLiveData: LiveData<RestClientResult<TvDetailResponse>>
    get() = _tvDetailLiveData

    private val _movieDetailLiveData = MutableLiveData<RestClientResult<MovieDetailResponse>>()
    val movieDetailLivedata: LiveData<RestClientResult<MovieDetailResponse>>
    get() = _movieDetailLiveData


    fun fetchTvDetailsByID(tvId: Int){
        viewModelScope.launch {
            val result = movieRepository.fetchTvDetails(tvId)
            _tvDetailLiveData.postValue(result)
        }
    }

    fun fetchMovieDetailsByID(movieId: Int){
        viewModelScope.launch {
            val result = movieRepository.fetchMovieDetails(movieId)
            _movieDetailLiveData.postValue(result)
        }
    }


//    fun fetchDetailsByID(id: Int){
//        viewModelScope.launch {
//            if (id == tv?.id){
//                val result = movieRepository.fetchTvDetails(id)
//                _tvDetailLiveData.postValue(result)
//            } else if (id == movie?.id){
//                val result = movieRepository.fetchMovieDetails(id)
//                _movieDetailLiveData.postValue(result)
//            }
//        }
//    }
}