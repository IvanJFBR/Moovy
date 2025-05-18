package com.interview.ivanjfbr.home.data.model

import com.google.gson.annotations.SerializedName

data class MoviesSectionResponse(
    @SerializedName("dates")
    val dates: DatesResponse?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("results")
    val results: List<MovieResponse>?,
    @SerializedName("total_results")
    val totalResults: Int?
)

data class DatesResponse(
    @SerializedName("maximum")
    val maximum: String?,
    @SerializedName("minimum")
    val minimum: String?
)