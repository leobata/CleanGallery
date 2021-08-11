package com.leobata.domain.usecase.photo

import com.leobata.domain.model.Photo

/**
 * Use case to load an specific photo
 */
interface LoadPhoto {
    suspend operator fun invoke(photoId: Long): Photo?
}