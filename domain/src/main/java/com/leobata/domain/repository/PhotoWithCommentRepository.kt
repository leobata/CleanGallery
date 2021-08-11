package com.leobata.domain.repository

import com.leobata.domain.model.PhotoWithComments
import kotlinx.coroutines.flow.Flow

/**
 * Interface to represent the implementation of Photo with comments repository.
 */
interface PhotoWithCommentRepository {
    /**
     * Gets a specific photo with comments by id.
     *
     * @param photoId the photo id
     *
     * @return the photo with comments
     */
    fun findPhotoWithCommentsById(photoId: Long): Flow<PhotoWithComments?>

    /**
     * Get all available photos with comments.
     *
     * @return all available photos with comments
     */
    fun findAllPhotosWithComments(): Flow<List<PhotoWithComments>>
}