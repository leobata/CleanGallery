package com.leobata.domain.usecase.photo.implementation

import com.leobata.domain.model.Photo
import com.leobata.domain.repository.PhotoRepository
import com.leobata.domain.usecase.photo.LoadAllPhotos
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadAllPhotosImpl @Inject constructor(private val photoRepository: PhotoRepository) :
    LoadAllPhotos {
    override fun invoke(): Flow<List<Photo>> =
        photoRepository.findAllPhotos()
}