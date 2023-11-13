package com.gachon.data.source.mainFeed

import com.gachon.domain.model.ThreadMainListResponse
import com.gachon.domain.repository.MainThreadRepository
import javax.inject.Inject

class MainFeedRepositoryImpl @Inject constructor(
    private val dataSource: MainFeedDataSource
) : MainThreadRepository {
    override suspend fun getThreadMainList(orderType: Int): ThreadMainListResponse =
        dataSource.getThreadMainList(orderType)
}