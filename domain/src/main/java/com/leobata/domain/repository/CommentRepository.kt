package com.leobata.domain.repository

import com.leobata.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    /**
     * Get all comments from a specific photo.
     *
     * @return all available comments
     */
    fun findCommentsByPhotoId(photoId: Long): Flow<List<Comment>>
}