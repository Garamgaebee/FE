package com.gachon.domain.repository

import com.gachon.domain.model.ThreadMainListResponse

interface MainThreadRepository {
    suspend fun getThreadMainList(orderType: Int): ThreadMainListResponse
}