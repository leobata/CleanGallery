package com.leobata.feature_photo.di

import com.leobata.domain.usecase.comment.LoadComments
import com.leobata.domain.usecase.comment.implementation.LoadCommentsImpl
import com.leobata.domain.usecase.photo.LoadAllPhotos
import com.leobata.domain.usecase.photo.implementation.LoadAllPhotosImpl
import com.leobata.domain.usecase.photowithcomment.LoadAllPhotosWithComments
import com.leobata.domain.usecase.photowithcomment.LoadPhotoWithComments
import com.leobata.domain.usecase.photowithcomment.implementation.LoadAllPhotosWithCommentsImpl
import com.leobata.domain.usecase.photowithcomment.implementation.LoadPhotoWithCommentsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FeatureModule {
    @Provides
    @Singleton
    fun providesLoadPhotosUseCase(loadAllPhotosImpl: LoadAllPhotosImpl): LoadAllPhotos =
        loadAllPhotosImpl

    @Provides
    @Singleton
    fun providesLoadPhotoWithCommentsUseCase(
        loadPhotoWithCommentsImpl: LoadPhotoWithCommentsImpl
    ): LoadPhotoWithComments = loadPhotoWithCommentsImpl

    @Provides
    @Singleton
    fun providesLoadAllPhotoWithCommentsUseCase(
        loadAllPhotoWithCommentsImpl: LoadAllPhotosWithCommentsImpl
    ): LoadAllPhotosWithComments = loadAllPhotoWithCommentsImpl

    @Provides
    @Singleton
    fun providesLoadCommentsUseCase(loadCommentsImpl: LoadCommentsImpl): LoadComments =
        loadCommentsImpl
}