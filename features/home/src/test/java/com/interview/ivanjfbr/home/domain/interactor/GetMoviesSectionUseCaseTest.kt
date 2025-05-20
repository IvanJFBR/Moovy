package com.interview.ivanjfbr.home.domain.interactor

import com.interview.ivanjfbr.home.data.model.MoviesSectionRequest
import com.interview.ivanjfbr.home.data.model.MoviesSectionResponse
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
class GetMoviesSectionUseCaseTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var getMoviesSectionUseCase: GetMoviesSectionUseCase

    @Before
    fun setUp() {
        getMoviesSectionUseCase = GetMoviesSectionUseCase(moviesRepository)
    }

    @Test
    fun `execute should return flow of movie response`() = runTest {
        // given
        val moviesSectionResponse = MoviesSectionResponse()
        whenever(moviesRepository.getSectionMoviesList(url = "url", page = 1)).thenReturn(
            flowOf(
                moviesSectionResponse
            )
        )

        // when
        val result =
            getMoviesSectionUseCase.execute(MoviesSectionRequest(url = "url", page = 1)).first()

        // then
        assertEquals(moviesSectionResponse, result)
    }

    @Test
    fun `execute should return error flow when an exception occurs`() = runTest {
        // Given
        val exception = Exception("An error occurred")
        val flow: Flow<MoviesSectionResponse> = flow { throw exception }
        given(moviesRepository.getSectionMoviesList(url = "url", page = 1)).willReturn(flow)

        // When
        val result = runCatching {
            getMoviesSectionUseCase.execute(
                MoviesSectionRequest(
                    url = "url",
                    page = 1
                )
            ).first()
        }

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}