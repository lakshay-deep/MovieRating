package com.lakshay.movierating.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lakshay.movierating.data.model.MovieResponse
import com.lakshay.movierating.data.model.RestClientResult
import com.lakshay.movierating.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
    ) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<RestClientResult<MovieResponse>>()
    val moviesLiveData: LiveData<RestClientResult<MovieResponse>>
        get() = _moviesLiveData

    fun fetchPopularMovies() {
        viewModelScope.launch {
           val result =  movieRepository.fetchPopularMovies()
            _moviesLiveData.postValue(result)
        }
    }
}