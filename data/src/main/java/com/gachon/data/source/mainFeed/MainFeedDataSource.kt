package com.gachon.data.source.mainFeed

import android.content.ContentValues
import android.util.Log
import com.gachon.data.remote.GaramgaebiService
import com.gachon.domain.model.ThreadMainListResponse
import com.gachon.domain.model.ThreadMainListResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MainFeedDataSource @Inject constructor(
    private val garamgaebiService: GaramgaebiService
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
    suspend fun getThreadMainList(orderType: Int): ThreadMainListResponse {
        var response = ThreadMainListResponse(
            code = "0",
            isSuccess = true,
            message = "",
            result = List(5) {
                ThreadMainListResult(
                    authorIdx = "",
                    authorImgUrl = "",
                    authorName= "작성자",
                    commentNumber= 0,
                    content= "게시물내용",
                    createdAt= "",
                    department= "학과",
                    imgs= listOf(),
                    isComment= false,
                    likeNumber= 1,
                    rootThreadIdx= "",
                    teamImgUrl= "",
                    teamName= "팀이름",
                    threadId= "",
                    type= ""
                )

            }
        )
        withContext(Dispatchers.IO) {
            runCatching {
                garamgaebiService.getThreadMainList(orderType)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "getThreadMainList Failed: $it")
            }
        }
        return response
    }
}