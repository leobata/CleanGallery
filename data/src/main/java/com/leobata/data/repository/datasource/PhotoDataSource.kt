package com.leobata.data.repository.datasource

import com.leobata.data.repository.model.Photo
import kotlinx.coroutines.flow.Flow

/**
 * Interface to represent the Photo data source implementation
 */
interface PhotoDataSource {
    /**
     * Get all available photos.
     *
     * @return all the available photos
     */
    fun loadAllPhotos(): Flow<List<Photo>>

    /**
     * Get an specific photo by id.
     *
     * @param photoId the Photo id
     * @return the selected photo
     */
    suspend fun loadPhotoById(photoId: Long): Photo?
}