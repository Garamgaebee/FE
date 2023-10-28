package com.gachon.domain.model

data class MainFeedListResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: List<MainFeedListResult>
)
data class MainFeedListResult(
    val authorIdx: String,
    val authorImgUrl: String,
    val authorName: String,
    val commentNumber: Int,
    val content: String,
    val createdAt: String,
    val department: String,
    val imgs: String,
    val isComment: Boolean,
    val likeNumber: Int,
    val rootThreadIdx: String,
    val teamImgUrl: String,
    val teamName: String,
    val threadId: String,
    val type: String
)