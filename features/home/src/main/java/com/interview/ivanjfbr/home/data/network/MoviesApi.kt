package com.interview.ivanjfbr.home.data.network

import com.interview.ivanjfbr.core.commons.Constants.LANG
import com.interview.ivanjfbr.home.data.model.MovieResponse
import com.interview.ivanjfbr.home.data.model.MoviesSectionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface MoviesApi {

    @GET
    suspend fun getMoviesSection(
        @Url url: String,
        @Query("page") page: Int? = null,
        @Query("language") language: String? = LANG,
    ): MoviesSectionResponse

    @GET("{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("language") language: String? = LANG,
    ): MovieResponse
}