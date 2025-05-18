package com.interview.ivanjfbr.home.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val movieId: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
)