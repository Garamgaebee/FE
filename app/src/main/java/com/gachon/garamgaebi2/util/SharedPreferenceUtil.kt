package com.gachon.garamgaebi2.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil(context: Context) {
    private val spfManager: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun spfClear() {
        spfManager.edit().clear().apply()
    }
    // accessToken 메서드
    fun checkUserToken(): Boolean = spfManager.contains("accessToken")
    fun getAccessToken(): String = spfManager.getString("accessToken", "").toString()
    fun setAccessToken(token: String) {
        spfManager.edit().putString("accessToken", "Bearer $token").apply()
    }
    fun getRefreshToken(): String = spfManager.getString("refreshToken", "").toString()
    fun setRefreshToken(token: String) {
        spfManager.edit().putString("refreshToken", "Bearer $token").apply()
    }

    // userId 메서드
    fun checkUserId() : Boolean = spfManager.contains("user_id")
    fun getUserId(): Long = spfManager.getLong("user_id", 0L)
    fun setUserId(userId: Long) {
        spfManager.edit().putLong("user_id", userId).apply()
    }
}