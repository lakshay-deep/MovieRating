package com.lakshay.movierating.data.network

import com.lakshay.movierating.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movie/popular?api_key=5e27dee69c9d0351682c4fc9124e8980")
    suspend fun fetchPopularMovies(): Response<MovieResponse>

    @GET("tv/popular?api_key=5e27dee69c9d0351682c4fc9124e8980")
    suspend fun fetchPopularTvShows(): Response<TvResponse>

    @GET("person/popular?api_key=5e27dee69c9d0351682c4fc9124e8980&language=en-US&page=1")
    suspend fun fetchPopularPerson(): Response<PersonResponse>

    @GET("trending/{media_type}/{time_window}?api_key=5e27dee69c9d0351682c4fc9124e8980")
    suspend fun fetchTrending(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String
    ): Response<TrendingResponse>

    @GET("tv/{tv_id}?api_key=5e27dee69c9d0351682c4fc9124e8980&language=en-US")
    suspend fun fetchTvDetails(
        @Path("tv_id") tvId: Int
    ): Response<TvDetailResponse>

    @GET("movie/{movie_id}?api_key=5e27dee69c9d0351682c4fc9124e8980&language=en-US")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieID: Int
    ): Response<MovieDetailResponse>

}