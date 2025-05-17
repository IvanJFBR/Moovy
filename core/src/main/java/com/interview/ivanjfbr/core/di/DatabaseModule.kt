package com.interview.ivanjfbr.core.di

import android.content.Context
import com.interview.ivanjfbr.core.commons.Constants.DB_MOOVY
import com.interview.ivanjfbr.core.data.local_db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_MOOVY).build()
    }
}