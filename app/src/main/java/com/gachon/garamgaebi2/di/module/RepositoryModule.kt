package com.gachon.garamgaebi2.di.module

import com.gachon.data.source.mainFeed.MainFeedRepositoryImpl
import com.gachon.domain.repository.MainThreadRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMainThreadRepository(
        mainFeedRepositoryImpl: MainFeedRepositoryImpl
    ): MainThreadRepository {
        return mainFeedRepositoryImpl
    }
}