package com.gachon.domain.model

data class ThreadMainListResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: List<ThreadMainListResult>
)
data class ThreadMainListResult(
    val authorIdx: String,
    val authorImgUrl: String,
    val authorName: String,
    val commentNumber: Int,
    val content: String,
    val createdAt: String,
    val department: String,
    val imgs: List<String>,
    val isComment: Boolean,
    val likeNumber: Int,
    val rootThreadIdx: String,
    val teamImgUrl: String,
    val teamName: String,
    val threadId: String,
    val type: String
)