package com.interview.ivanjfbr.home.domain.interactor

import com.interview.ivanjfbr.home.data.model.MovieResponse
import com.interview.ivanjfbr.home.domain.repository.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailUseCaseTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var getMovieDetailUseCase: GetMovieDetailsUseCase

    @Before
    fun setUp() {
        getMovieDetailUseCase = GetMovieDetailsUseCase(moviesRepository)
    }

    @Test
    fun `execute should return flow of movie response`() = runTest {
        // given
        val movieResponse = MovieResponse()
        whenever(moviesRepository.getMovieDetails("id")).thenReturn(flowOf(movieResponse))

        // when
        val result = getMovieDetailUseCase.execute("id").first()

        // then
        assertEquals(movieResponse, result)
    }

    @Test
    fun `execute should return error flow when an exception occurs`() = runTest {
        // Given
        val exception = Exception("An error occurred")
        val flow: Flow<MovieResponse> = flow { throw exception }
        given(moviesRepository.getMovieDetails("id")).willReturn(flow)

        // When
        val result = runCatching { getMovieDetailUseCase.execute("id").first() }

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}