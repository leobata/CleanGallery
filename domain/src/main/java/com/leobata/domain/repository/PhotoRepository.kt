package com.leobata.domain.repository

import com.leobata.domain.model.Photo
import kotlinx.coroutines.flow.Flow

/**
 * Interface to represent the implementation of Photo repository.
 */
interface PhotoRepository {
    /**
     * Gets a specific photo by id.
     *
     * @param photoId the photo id
     */
    suspend fun findPhotoById(photoId: Long): Photo?

    /**
     * Get all available photos.
     *
     * @return all available photos
     */
    fun findAllPhotos(): Flow<List<Photo>>
}