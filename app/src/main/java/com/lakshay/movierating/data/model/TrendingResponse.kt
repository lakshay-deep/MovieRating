package com.lakshay.movierating.data.model

import com.google.gson.annotations.SerializedName

data class TrendingResponse(

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<Trending>,

    @SerializedName("total_pages")
    val total_pages: Int,

    @SerializedName("total_results")
    val total_results: Int
)