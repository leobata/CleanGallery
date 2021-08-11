package com.leobata.data.remote.di

import com.leobata.data.remote.api.ApiHelper
import com.leobata.data.remote.api.ApiHelperImpl
import com.leobata.data.remote.api.ApiService
import com.leobata.data.remote.datasource.CommentRemoteDataSource
import com.leobata.data.remote.datasource.PhotoRemoteDataSource
import com.leobata.data.remote.mapper.CommentMapper
import com.leobata.data.remote.mapper.PhotoMapper
import com.leobata.data.repository.datasource.CommentDataSource
import com.leobata.data.repository.datasource.PhotoDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemotePhotoDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteCommentDataSource

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    @Singleton
    fun provideOkHttpClient() = run {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @RemotePhotoDataSource
    @Provides
    fun provideRemotePhotoDataSource(
        apiHelper: ApiHelperImpl,
        photoMapper: PhotoMapper
    ): PhotoDataSource =
        PhotoRemoteDataSource(apiHelper, photoMapper)

    @RemoteCommentDataSource
    @Provides
    fun provideRemoteCommentDataSource(
        apiHelper: ApiHelperImpl,
        commentMapper: CommentMapper
    ): CommentDataSource =
        CommentRemoteDataSource(apiHelper, commentMapper)
}