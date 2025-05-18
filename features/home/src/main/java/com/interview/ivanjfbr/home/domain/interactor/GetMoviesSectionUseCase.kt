package com.interview.ivanjfbr.home.domain.interactor

import com.interview.ivanjfbr.core.domain.BaseUseCase
import com.interview.ivanjfbr.home.data.model.MoviesSectionRequest
import com.interview.ivanjfbr.home.data.model.MoviesSectionResponse
import com.interview.ivanjfbr.home.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesSectionUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<MoviesSectionRequest, Flow<MoviesSectionResponse>>() {

    override suspend fun execute(params: MoviesSectionRequest): Flow<MoviesSectionResponse> {
        return moviesRepository.getSectionMoviesList(url = params.url, page = params.page)
    }
}