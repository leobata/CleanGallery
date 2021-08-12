package com.leobata.data.local.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.leobata.data.local.dao.CommentDao
import com.leobata.data.local.dao.PhotoDao
import com.leobata.data.local.database.GalleryDatabase
import com.leobata.data.local.datasource.CommentLocalDataSource
import com.leobata.data.local.datasource.PhotoLocalDataSource
import com.leobata.data.local.mapper.CommentMapper
import com.leobata.data.local.mapper.PhotoMapper
import com.leobata.data.repository.datasource.CommentDataSource
import com.leobata.data.repository.datasource.PhotoDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: GalleryDatabase.Callback) =
        Room.databaseBuilder(application, GalleryDatabase::class.java, "photo_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    @Singleton
    fun providesPhotoDao(database: GalleryDatabase) =
        database.photoDao()

    @Provides
    @Singleton
    fun providesCommentsDao(database: GalleryDatabase) =
        database.commentDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun providesApplicationScope() = CoroutineScope(SupervisorJob())

    @LocalPhotoDataSource
    @Provides
    fun provideLocalPhotoDataSource(
        photoDao: PhotoDao,
        photoMapper: PhotoMapper
    ): PhotoDataSource =
        PhotoLocalDataSource(photoDao, photoMapper)

    @LocalCommentDataSource
    @Provides
    fun provideLocalCommentDataSource(
        commentDao: CommentDao,
        commentMapper: CommentMapper
    ): CommentDataSource =
        CommentLocalDataSource(commentDao, commentMapper)

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalPhotoDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalCommentDataSource