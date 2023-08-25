package com.gachon.garamgaebi2.di.module

import com.gachon.garamgaebi2.di.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //전체 application 에서 사용되는 모듈
object NetworkModule {

    // 로그인 API Retrofit
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class GaramgaebiLoginRetrofit

    // 외 API Retrofit
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class GaramgaebiRetrofit


    @Provides
    @Singleton
    fun provideAuthInterceptor(authInterceptor: AuthInterceptor) : Interceptor = authInterceptor

    @Provides
    @Singleton
    @GaramgaebiLoginRetrofit
    fun provideLoginRetrofit(gsonConverterFactory: GsonConverterFactory, @PureumLoginRetrofit client: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()

    @Provides
    @Singleton
    @PureumLoginRetrofit
    fun provideLoginOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    @PureumRetrofit
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, @PureumRetrofit client: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()

    @Provides
    @Singleton
    @PureumRetrofit
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()
}
