package com.interview.ivanjfbr.home.data.model

import com.google.gson.annotations.SerializedName

data class MoviesSectionResponse(
    @SerializedName("dates")
    val dates: DatesResponse? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("results")
    val results: List<MovieResponse>? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)

data class DatesResponse(
    @SerializedName("maximum")
    val maximum: String? = null,
    @SerializedName("minimum")
    val minimum: String? = null
)