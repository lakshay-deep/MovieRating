package com.lakshay.movierating.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lakshay.movierating.data.model.RestClientResult
import com.lakshay.movierating.data.model.TvResponse
import com.lakshay.movierating.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val movieRepository: MovieRepository
    ): ViewModel() {

    private val _tvLiveData = MutableLiveData<RestClientResult<TvResponse>>()
    val tvLiveData: LiveData<RestClientResult<TvResponse>>
    get() = _tvLiveData

    fun fetchPopularTvShows(){
        viewModelScope.launch {
            val result = movieRepository.fetchPopularTvShows()
            _tvLiveData.postValue(result)
        }
    }
}