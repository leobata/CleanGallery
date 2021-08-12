package com.leobata.data.local.datasource

import com.leobata.data.local.dao.CommentDao
import com.leobata.data.local.mapper.CommentMapper
import com.leobata.data.repository.datasource.CommentDataSource
import com.leobata.data.repository.model.Comment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class CommentLocalDataSource @Inject constructor(
    private val commentDao: CommentDao,
    private val commentMapper: CommentMapper
) : CommentDataSource {

    override fun loadCommentsByPhotoId(photoId: Long): Flow<List<Comment>> {
        return flow {
            commentDao.getCommentByPhotoId(photoId).collect {
                emit(it.map { localComment -> commentMapper.toRepo(localComment) })
            }
        }
    }

    override suspend fun addAllComments(commentList: List<Comment>) {
        commentDao.insertAllComments(commentMapper.toLocal(commentList))
    }
}