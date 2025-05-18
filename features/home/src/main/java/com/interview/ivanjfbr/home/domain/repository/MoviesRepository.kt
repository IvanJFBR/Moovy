package com.interview.ivanjfbr.home.domain.repository

import com.interview.ivanjfbr.home.data.model.MoviesSectionResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getSectionMoviesList(url: String, page: Int): Flow<MoviesSectionResponse>
}