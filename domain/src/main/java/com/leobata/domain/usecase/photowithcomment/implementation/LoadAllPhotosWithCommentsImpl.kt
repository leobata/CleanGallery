package com.leobata.domain.usecase.photowithcomment.implementation

import com.leobata.domain.model.PhotoWithComments
import com.leobata.domain.repository.PhotoWithCommentRepository
import com.leobata.domain.usecase.photowithcomment.LoadAllPhotosWithComments
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadAllPhotosWithCommentsImpl @Inject constructor(
    private val photoWithCommentRepository: PhotoWithCommentRepository
) : LoadAllPhotosWithComments {
    override fun invoke(): Flow<List<PhotoWithComments>> =
        photoWithCommentRepository.findAllPhotosWithComments()
}