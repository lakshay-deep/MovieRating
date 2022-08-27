package com.lakshay.movierating.data.network

import com.lakshay.movierating.data.BaseDataSource
import com.lakshay.movierating.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) : BaseDataSource() {

    suspend fun fetchPopularMovies() = getResult {
        movieService.fetchPopularMovies()
    }

    suspend fun fetchPopularTvShows() = getResult {
        movieService.fetchPopularTvShows()
    }

    suspend fun fetchPopularPerson() = getResult {
        movieService.fetchPopularPerson()
    }

    suspend fun fetchTrending(mediaType: String, timeWindow: String) = getResult {
        movieService.fetchTrending(mediaType, timeWindow)
    }

    suspend fun fetchTvDetails(tvID: Int) = getResult {
        movieService.fetchTvDetails(tvID)
    }

    suspend fun fetchMovieDetails(movieID: Int) = getResult {
        movieService.fetchMovieDetails(movieID)
    }

}