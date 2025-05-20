package com.interview.ivanjfbr.home.domain.interactor

import androidx.paging.PagingData
import com.interview.ivanjfbr.core.domain.BaseUseCase
import com.interview.ivanjfbr.home.data.model.MovieResponse
import com.interview.ivanjfbr.home.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagingMoviesListUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<String, Flow<PagingData<MovieResponse>>>() {

    override suspend fun execute(params: String): Flow<PagingData<MovieResponse>> {
        return moviesRepository.getPagingMoviesList(url = params)
    }
}