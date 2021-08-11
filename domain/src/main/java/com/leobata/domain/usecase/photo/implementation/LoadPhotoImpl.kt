package com.leobata.domain.usecase.photo.implementation

import com.leobata.domain.model.Photo
import com.leobata.domain.repository.PhotoRepository
import com.leobata.domain.usecase.photo.LoadPhoto
import javax.inject.Inject

class LoadPhotoImpl @Inject constructor(private val photoRepository: PhotoRepository) : LoadPhoto {
    override suspend fun invoke(photoId: Long): Photo? =
        photoRepository.findPhotoById(photoId)
}