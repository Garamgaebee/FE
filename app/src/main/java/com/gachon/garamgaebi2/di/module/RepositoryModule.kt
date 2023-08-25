package com.gachon.garamgaebi2.di.module

import com.gachon.data.source.home.HomeDataSource
import com.gachon.data.source.profile.ProfileDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

//    @Provides
//    @Singleton
//    fun providesLoginRepository(loginDataSource: LoginDataSource) : LoginRepository =
//        LoginRepositoryImpl(loginDataSource)
//
//    @Provides
//    @Singleton
//    fun providesHomeRepository(homeDataSource: HomeDataSource) : HomeRepository =
//        HomeRepositoryImpl(homeDataSource)
//
//    @Provides
//    @Singleton
//    fun providesProfileRepository(profileDataSource: ProfileDataSource) : ProfileRepository =
//        ProfileRepositoryImpl(profileDataSource)
}