package com.gachon.data.source.mainFeed

import android.content.ContentValues
import android.util.Log
import com.gachon.data.remote.GaramgaebiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MainFeedDataSource @Inject constructor(
    private val service: GaramgaebiService
)  {
    // 예시
    /*suspend fun setPurposeTime(userId: Long, purposeTime: Int): DefaultResponse {
        var response = DefaultResponse(0, true, "", "")
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.setPurposeTime(userId, SetUsageTimeReq((purposeTime / 60).toString(), (purposeTime % 60).toString()))
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "setPurposeTime Failed: $it")
            }
        }
        return response
    }*/
}