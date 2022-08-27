package com.lakshay.movierating.data.repository

import com.lakshay.movierating.data.model.*
import com.lakshay.movierating.data.network.MovieRemoteDataSource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun fetchPopularMovies() = movieRemoteDataSource.fetchPopularMovies()

    override suspend fun fetchPopularTvShows() = movieRemoteDataSource.fetchPopularTvShows()

    override suspend fun fetchPopularPerson() = movieRemoteDataSource.fetchPopularPerson()

    override suspend fun fetchTrending(
        mediaType: String,
        timeWindow: String
    ) = movieRemoteDataSource.fetchTrending(mediaType, timeWindow)

    override suspend fun fetchTvDetails(tvId: Int) = movieRemoteDataSource.fetchTvDetails(tvId)

    override suspend fun fetchMovieDetails(movieID: Int) = movieRemoteDataSource.fetchMovieDetails(movieID)

}