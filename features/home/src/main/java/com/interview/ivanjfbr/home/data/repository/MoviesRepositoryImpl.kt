package com.interview.ivanjfbr.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.interview.ivanjfbr.core.data.paging.MoviesPagingSource
import com.interview.ivanjfbr.home.data.model.MovieResponse
import com.interview.ivanjfbr.home.data.model.MoviesSectionResponse
import com.interview.ivanjfbr.home.data.network.MoviesApi
import com.interview.ivanjfbr.home.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesRepository {
    override suspend fun getSectionMoviesList(url: String, page: Int): Flow<MoviesSectionResponse> {
        return flow {
            emit(moviesApi.getMoviesSection(url, page))
        }
    }

    override suspend fun getPagingMoviesList(
        url: String
    ): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                MoviesPagingSource { page ->
                    moviesApi.getMoviesSection(
                        url = url,
                        page = page
                    ).results ?: emptyList()
                }
            }
        ).flow
    }

    override suspend fun getMovieDetails(movieId: String): Flow<MovieResponse> {
        return flow {
            emit(moviesApi.getMovieDetails(movieId))
        }
    }
}