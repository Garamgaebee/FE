package com.gachon.domain.usecase

import javax.inject.Inject
import com.gachon.domain.model.ThreadMainListResponse
import com.gachon.domain.repository.MainThreadRepository

class GetThreadMainListUseCase @Inject constructor(
    private val mainThreadRepository: MainThreadRepository
) {
    suspend operator fun invoke(orderType: Int): ThreadMainListResponse {
        return mainThreadRepository.getThreadMainList(orderType)
    }
}
