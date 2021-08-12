package com.leobata.data.remote.datasource

import com.leobata.data.remote.api.ApiHelper
import com.leobata.data.remote.mapper.CommentMapper
import com.leobata.data.repository.datasource.CommentDataSource
import com.leobata.data.repository.model.Comment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class CommentRemoteDataSource @Inject constructor(
    private val apiHelper: ApiHelper,
    private val commentMapper: CommentMapper
) : CommentDataSource {

    override fun loadCommentsByPhotoId(photoId: Long): Flow<List<Comment>> {
        return flow {
            var comments = listOf<Comment>()
            apiHelper.getAllPhotoComments(photoId).body()?.let {
                comments = it.map { comment -> commentMapper.toRepo(comment, photoId) }
            }
            emit(comments)
        }
    }

    override suspend fun addAllComments(commentList: List<Comment>) {}
}