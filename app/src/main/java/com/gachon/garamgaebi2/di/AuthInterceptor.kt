package com.gachon.garamgaebi2.di

import android.util.Log
import androidx.datastore.dataStore
import com.gachon.garamgaebi2.di.GaramgaebiApplication.Companion.dsManager
import kotlinx.coroutines.runBlocking

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        var token = ""
        token = runBlocking{
            dsManager.getUserAccessToken()?.first().toString()
        }

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