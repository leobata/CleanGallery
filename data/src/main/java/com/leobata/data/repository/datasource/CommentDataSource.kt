package com.leobata.data.repository.datasource

import com.leobata.data.repository.model.Comment
import kotlinx.coroutines.flow.Flow

/**
 * Interface to represent the Comment data source implementation
 */
interface CommentDataSource {
    /**
     * Get all available comments for the given photo id.
     *
     * @return all the available comments
     */
    fun loadCommentsByPhotoId(photoId: Long): Flow<List<Comment>>

    /**
     * Add comment list.
     *
     * @param commentList the Comment list
     */
    suspend fun addAllComments(commentList: List<Comment>)
}