package com.lakshay.movierating.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lakshay.movierating.data.model.RestClientResult
import com.lakshay.movierating.data.model.TrendingResponse
import com.lakshay.movierating.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel(){

    private val _trendingLiveData = MutableLiveData<RestClientResult<TrendingResponse>>()
    val trendingLiveData: LiveData<RestClientResult<TrendingResponse>>
    get() = _trendingLiveData

    fun fetchTrending(mediaType: String, timeWindow: String){
        viewModelScope.launch {
            val result = movieRepository.fetchTrending(mediaType, timeWindow)
                _trendingLiveData.postValue(result)
        }
    }
}