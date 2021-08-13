package com.leobata.data.repository

import android.app.Application
import com.leobata.data.local.di.LocalCommentDataSource
import com.leobata.data.local.preferences.PreferencesHelper
import com.leobata.data.remote.di.RemoteCommentDataSource
import com.leobata.data.repository.datasource.CommentDataSource
import com.leobata.data.repository.mapper.CommentMapper
import com.leobata.domain.model.Comment
import com.leobata.domain.repository.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

internal class CommentRepositoryImpl @Inject constructor(
    @RemoteCommentDataSource private val commentRemoteDataSource: CommentDataSource,
    @LocalCommentDataSource private val commentLocalDataSource: CommentDataSource,
    private val commentMapper: CommentMapper,
    private val application: Application
) : CommentRepository {
    override fun findCommentsByPhotoId(photoId: Long): Flow<List<Comment>> {
        return flow {
            commentRemoteDataSource.loadCommentsByPhotoId(photoId)
                .catch {
                    if (!PreferencesHelper.isOfflineDataExpired(application)) {
                        commentLocalDataSource.loadCommentsByPhotoId(photoId)
                            .collect { localComments ->
                                if (localComments.isNotEmpty()) {
                                    emit(commentMapper.toDomain(localComments))
                                } else {
                                    throw Exception("No comments values found")
                                }
                            }
                    } else {
                        throw Exception("No comments values found")
                    }
                }
                .collect { remoteComments ->
                    PreferencesHelper.setLastSyncTime(application)
                    commentLocalDataSource.addAllComments(remoteComments)
                    commentLocalDataSource.loadCommentsByPhotoId(photoId).collect { localComments ->
                        emit(commentMapper.toDomain(localComments))
                    }
                }
        }.flowOn(Dispatchers.IO)
    }
}