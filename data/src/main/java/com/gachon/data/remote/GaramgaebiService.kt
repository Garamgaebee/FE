package com.gachon.data.remote

import com.gachon.domain.model.ThreadMainListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
interface GaramgaebiService {

    // 회원 등록 API
    @POST("/api/feign/member")
    suspend fun postFeignMember()

    // 회원 조회 API
    @GET("/api/members/{member-idx}")
    suspend fun getMemberInfo(@Path("member-idx") memberIdx: Int)

    // 회워 탈퇴 API
    @DELETE("/api/members/{member-idx}")
    suspend fun deleteMember(@Path("member-idx") memberIdx: Int)

    // 프로필 이미지 등록 API
    @Multipart
    @POST("/api/members/{member-idx}/image")
    suspend fun postProfileImage(
        @Path("member-idx") memberIdx: Int,
        @Part("info")info : RequestBody,
        @Part image : MultipartBody.Part?
    )

    // 프로필 이미지 수정 API
    @Multipart
    @PATCH("/api/members/{member-idx}/image")
    suspend fun patchProfileImage(
        @Path("member-idx") memberIdx: Int,
        @Part("info")info : RequestBody,
        @Part image : MultipartBody.Part?
    )

    // 이미지 등록 요청 API
    @POST("api/feign/member/image")
    suspend fun postImageRequest()

    // 이미지 삭제 요청 API
    @DELETE("api/feign/member/image")
    suspend fun deleteImageRequest()

    // 회원 Feign 조회 API
    @GET("/api/feign/members")
    suspend fun getMemberFeignInfo()

    // 회원 닉네임 중복 확인 Feign API
    @GET("/api/feign/members/duplicate/")
    suspend fun getMemberFeignNicknameDuplication(@Query("nickname") nickname: String)

    // 소셜 로그인 API
    @POST("/api/auth/login/oauth/{provider}")
    suspend fun postSocialLogin(@Path("provider") provider: String, @Query("authorization_code") authorizationCode: String)

    // Token 재발급 API
    @POST("/api/auth/refresh")
    suspend fun postTokenRefresh()

    // 자체 로그인 API
    @POST("/api/auth/login")
    suspend fun postLogin(@Body body: RequestBody)

    // 이메일 인증 코드 발송 API
    @POST("/api/auth/mail")
    suspend fun postEmailAuthCode(@Body body: RequestBody)

    // 로그 아웃 API
    @POST("/api/auth/logout")
    suspend fun postLogout()

    // 이메일 인증 코드 확인 API
    @POST("/api/auth/mail/check")
    suspend fun postEmailAuthCodeCheck(@Body body: RequestBody)

    // 회원가입 API
    @POST("/api/auth/members")
    suspend fun postMemberRegister(@Body body: RequestBody)

    // 닉네임 중복 검사 API
    @POST("/api/auth/members/nickname/check")
    suspend fun postNicknameDuplicationCheck(@Body body: RequestBody)

    // 회원 탈퇴 API
    @DELETE("/api/auth/members")
    suspend fun deleteMemberWithdrawal()

    // 이미지 저장 API
    @Multipart
    @POST("/api/feign/images")
    suspend fun postSaveImage(@Part image : MultipartBody.Part?)

    // 이미지 삭제 API
    @DELETE("/api/feign/images")
    suspend fun deleteImage()

    // 스레드 생성 API
    @POST("/api/threads")
    suspend fun postCreateThread(@Body body: RequestBody)

    // 스레드 삭제 API
    @DELETE("/api/threads")
    suspend fun deleteThread(@Body body: RequestBody)

    // 스레드 메인 리스트 조회 API
    @GET("/api/threads")
    suspend fun getThreadMainList(@Query("order-type") orderType :Int) : ThreadMainListResponse

    // 스레드 팀 조회 API
    @GET("/api/threads/team")
    suspend fun getThreadTeamList(@Query("page") page: Int, @Query("limit") limit: Int)

    // 스레드, 댓글 좋아요 생성
    @POST("/api/threads/likes")
    suspend fun postCreateThreadLike(@Body body: RequestBody)

    // 스레드, 댓글 좋아요 삭제
    @DELETE("/api/threads/likes")
    suspend fun deleteThreadLike(@Body body: RequestBody)

    // 댓글 생성 API
    @POST("/api/threads/reply")
    suspend fun postCreateReply(@Body body: RequestBody)

    // 댓글 삭제 API
    @DELETE("/api/threads/reply")
    suspend fun deleteReply(@Body body: RequestBody)

    // 팀 생성 API

    // 팀 삭제 API

    // 팀 수정 API

    // 팀 메인 화면 API

    // 팀 가입 신청 API

    // 팀 공지 생성 API

    // 팀 공지 수정 API

    // 팀 공지 삭제 API

    // 팀 가입 승인 API

    // 팀 강퇴 API

    // 팀 정보 조회 API

}