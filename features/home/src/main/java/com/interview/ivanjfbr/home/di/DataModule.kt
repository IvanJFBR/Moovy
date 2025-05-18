package com.interview.ivanjfbr.home.di

import com.interview.ivanjfbr.home.data.network.MoviesApi
import com.interview.ivanjfbr.home.data.repository.MoviesRepositoryImpl
import com.interview.ivanjfbr.home.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(apiService: MoviesApi): MoviesRepository {
        return MoviesRepositoryImpl(apiService)
    }
}