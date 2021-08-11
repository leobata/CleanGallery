package com.leobata.domain.usecase.photowithcomment.implementation

import com.leobata.domain.model.PhotoWithComments
import com.leobata.domain.repository.PhotoWithCommentRepository
import com.leobata.domain.usecase.photowithcomment.LoadPhotoWithComments
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadPhotoWithCommentsImpl @Inject constructor(
    private val photoWithCommentRepository: PhotoWithCommentRepository
) : LoadPhotoWithComments {
    override fun invoke(photoId: Long): Flow<PhotoWithComments?> =
        photoWithCommentRepository.findPhotoWithCommentsById(photoId)
}