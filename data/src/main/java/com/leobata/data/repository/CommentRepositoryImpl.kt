package com.leobata.data.repository

import com.leobata.data.repository.datasource.CommentDataSource
import com.leobata.data.repository.mapper.CommentMapper
import com.leobata.domain.model.Comment
import com.leobata.domain.repository.CommentRepository
import com.leobata.data.remote.di.RemoteCommentDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CommentRepositoryImpl @Inject constructor(
    @RemoteCommentDataSource private val commentRemoteDataSource: CommentDataSource,
    private val commentMapper: CommentMapper
) : CommentRepository {

    override fun findCommentsByPhotoId(photoId: Long): Flow<List<Comment>> =
        commentRemoteDataSource.loadCommentsByPhotoId(photoId).map { commentMapper.toDomain(it) }
}