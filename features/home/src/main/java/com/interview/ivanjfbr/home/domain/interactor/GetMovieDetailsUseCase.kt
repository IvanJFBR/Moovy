package com.interview.ivanjfbr.home.domain.interactor

import com.interview.ivanjfbr.core.domain.BaseUseCase
import com.interview.ivanjfbr.home.data.model.MovieResponse
import com.interview.ivanjfbr.home.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<String, Flow<MovieResponse>>() {

    override suspend fun execute(params: String): Flow<MovieResponse> {
        return moviesRepository.getMovieDetails(movieId = params)
    }
}