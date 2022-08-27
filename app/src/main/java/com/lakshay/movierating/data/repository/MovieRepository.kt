package com.lakshay.movierating.data.repository

import com.lakshay.movierating.data.model.*
import okhttp3.MediaType

interface MovieRepository {

    suspend fun fetchPopularMovies(): RestClientResult<MovieResponse>

    suspend fun fetchPopularTvShows(): RestClientResult<TvResponse>

    suspend fun fetchPopularPerson(): RestClientResult<PersonResponse>

    suspend fun fetchTrending(mediaType: String, timeWindow: String): RestClientResult<TrendingResponse>

    suspend fun fetchTvDetails(tvId: Int): RestClientResult<TvDetailResponse>

    suspend fun fetchMovieDetails(movieID: Int): RestClientResult<MovieDetailResponse>
}