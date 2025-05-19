package com.interview.ivanjfbr.home.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val movieId: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("genres")
    val genres: List<GenresItem?>? = null,
    @SerializedName("overview")
    val overview: String? = null,
)

data class GenresItem(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("id")
    val id: Int? = null
)