package com.leobata.domain.usecase.photo

import com.leobata.domain.model.Photo
import kotlinx.coroutines.flow.Flow

/**
 * Use case to load all available photos
 */
interface LoadAllPhotos {
    /**
     * Loads all photos.
     *
     * @return a mutable list of all photos
     */
    operator fun invoke(): Flow<List<Photo>>
}