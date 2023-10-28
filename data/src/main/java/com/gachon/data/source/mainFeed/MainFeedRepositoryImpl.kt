package com.gachon.data.source.mainFeed

import com.gachon.domain.repository.MainFeedRepository
import javax.inject.Inject

class MainFeedRepositoryImpl @Inject constructor(
    private val dataSource: MainFeedDataSource
) : MainFeedRepository {
    /*override suspend fun setPurposeTime(userId: Long, purposeTime: Int): DefaultResponse =
        dataSource.setPurposeTime(userId, purposeTime)*/
}