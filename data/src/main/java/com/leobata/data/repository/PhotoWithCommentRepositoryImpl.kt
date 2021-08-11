package com.leobata.data.repository

import com.leobata.data.remote.di.RemoteCommentDataSource
import com.leobata.data.remote.di.RemotePhotoDataSource
import com.leobata.data.repository.datasource.CommentDataSource
import com.leobata.data.repository.datasource.PhotoDataSource
import com.leobata.data.repository.mapper.CommentMapper
import com.leobata.data.repository.mapper.PhotoMapper
import com.leobata.domain.model.PhotoWithComments
import com.leobata.domain.repository.PhotoWithCommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class PhotoWithCommentRepositoryImpl @Inject constructor(
    @RemotePhotoDataSource private val photoRemoteDataSource: PhotoDataSource,
    @RemoteCommentDataSource private val commentRemoteDataSource: CommentDataSource,
    private val photoMapper: PhotoMapper,
    private val commentMapper: CommentMapper
) : PhotoWithCommentRepository {

    override fun findPhotoWithCommentsById(photoId: Long): Flow<PhotoWithComments?> {
        return flow {
            var photoWithComments: PhotoWithComments?
            photoRemoteDataSource.loadPhotoById(photoId)?.let { remotePhoto ->
                val photo = photoMapper.toDomain(remotePhoto)
                commentRemoteDataSource.loadCommentsByPhotoId(photoId).collect {
                    val comments = commentMapper.toDomain(it)
                    photoWithComments = PhotoWithComments(photo, comments)
                    emit(photoWithComments)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun findAllPhotosWithComments(): Flow<List<PhotoWithComments>> {
        return flow {
            val photosWithComments: ArrayList<PhotoWithComments> = arrayListOf()
            photoRemoteDataSource.loadAllPhotos().collect { remotePhotos ->
                val photos = photoMapper.toDomain(remotePhotos)
                photos.map { photo ->
                    commentRemoteDataSource.loadCommentsByPhotoId(photo.id).collect {
                        val comments = commentMapper.toDomain(it)
                        photosWithComments.add(PhotoWithComments(photo, comments))
                        emit(photosWithComments)
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}