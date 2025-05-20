package com.interview.ivanjfbr.home.data.repository

import com.interview.ivanjfbr.home.data.network.MoviesApi
import com.interview.ivanjfbr.home.data.repository.models.MockModels
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryImplTest {

    @Mock
    private lateinit var moviesApi: MoviesApi
    private lateinit var moviesRepositoryImpl: MoviesRepositoryImpl

    @Before
    fun setUp() {
        moviesRepositoryImpl = MoviesRepositoryImpl(moviesApi)
    }

    @Test
    fun `getSectionMoviesList should return the correct data`() = runTest {
        // Given
        val expectedResponse = MockModels.dummyMoviesSectionResponse
        whenever(moviesApi.getMoviesSection(url = "url", page = 1)).thenReturn(expectedResponse)

        // When
        val actualResponse = moviesRepositoryImpl.getSectionMoviesList(url = "url", page = 1).first()

        // Then
        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `getMovieDetails should return the correct data`() = runTest {
        // Given
        val expectedResponse = MockModels.dummyMovieDetailsResponse
        whenever(moviesApi.getMovieDetails(movieId = "id")).thenReturn(expectedResponse)

        // When
        val actualResponse = moviesRepositoryImpl.getMovieDetails(movieId = "id").first()

        // Then
        assertEquals(expectedResponse, actualResponse)
    }
}