package com.leobata.data.repository.di

import com.leobata.data.repository.CommentRepositoryImpl
import com.leobata.data.repository.PhotoRepositoryImpl
import com.leobata.data.repository.PhotoWithCommentRepositoryImpl
import com.leobata.domain.repository.CommentRepository
import com.leobata.domain.repository.PhotoRepository
import com.leobata.domain.repository.PhotoWithCommentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    internal fun providePhotoRepository(photoRepositoryImpl: PhotoRepositoryImpl): PhotoRepository =
        photoRepositoryImpl

    @Provides
    @Singleton
    internal fun provideCommentRepository(
        commentRepositoryImpl: CommentRepositoryImpl
    ): CommentRepository = commentRepositoryImpl

    @Provides
    @Singleton
    internal fun providePhotoWithCommentRepository(
        photoWithCommentRepositoryImpl: PhotoWithCommentRepositoryImpl
    ): PhotoWithCommentRepository = photoWithCommentRepositoryImpl
}