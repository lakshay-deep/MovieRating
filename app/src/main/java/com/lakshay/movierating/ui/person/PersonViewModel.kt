package com.lakshay.movierating.ui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lakshay.movierating.data.model.PersonResponse
import com.lakshay.movierating.data.model.RestClientResult
import com.lakshay.movierating.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel()  {

    private val _personLiveData = MutableLiveData<RestClientResult<PersonResponse>>()
    val personLiveData : LiveData<RestClientResult<PersonResponse>>
    get() = _personLiveData

    fun fetchPopularPerson(){
        viewModelScope.launch {
            val result = movieRepository.fetchPopularPerson()
            _personLiveData.postValue(result)
        }
    }
}