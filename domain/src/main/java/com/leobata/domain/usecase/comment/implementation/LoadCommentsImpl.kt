package com.leobata.domain.usecase.comment.implementation

import com.leobata.domain.model.Comment
import com.leobata.domain.repository.CommentRepository
import com.leobata.domain.usecase.comment.LoadComments
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadCommentsImpl @Inject constructor(private val commentRepository: CommentRepository) :
    LoadComments {
    override fun invoke(photoId: Long): Flow<List<Comment>> =
        commentRepository.findCommentsByPhotoId(photoId)
}