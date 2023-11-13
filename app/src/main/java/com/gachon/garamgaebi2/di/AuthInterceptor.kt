package com.gachon.garamgaebi2.di


import android.util.Log
import com.gachon.garamgaebi2.di.GaramgaebiApplication.Companion.spfManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = spfManager.getAccessToken()
        val authRequest = if (token.isEmpty()){
            originalRequest.newBuilder()
                .build()
        } else {
            originalRequest.newBuilder()
                .addHeader("Authorization", token)
                .build().also {
                    Log.e("okhttpHeader", token)
                }
        }
        return chain.proceed(authRequest)
    }
}

