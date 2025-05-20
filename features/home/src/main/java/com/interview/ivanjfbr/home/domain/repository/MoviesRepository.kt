package com.interview.ivanjfbr.home.domain.repository

import androidx.paging.PagingData
import com.interview.ivanjfbr.home.data.model.MovieResponse
import com.interview.ivanjfbr.home.data.model.MoviesSectionResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getSectionMoviesList(url: String, page: Int): Flow<MoviesSectionResponse>

    suspend fun getPagingMoviesList(url: String): Flow<PagingData<MovieResponse>>

    suspend fun getMovieDetails(movieId: String): Flow<MovieResponse>
}