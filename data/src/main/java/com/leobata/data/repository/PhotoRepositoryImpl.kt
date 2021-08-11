package com.leobata.data.repository

import com.leobata.data.repository.datasource.PhotoDataSource
import com.leobata.data.repository.mapper.PhotoMapper
import com.leobata.domain.model.Photo
import com.leobata.domain.repository.PhotoRepository
import com.leobata.data.remote.di.RemotePhotoDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PhotoRepositoryImpl @Inject constructor(
    @RemotePhotoDataSource private val photoRemoteDataSource: PhotoDataSource,
    private val photoMapper: PhotoMapper
) : PhotoRepository {
    override suspend fun findPhotoById(photoId: Long): Photo? =
        photoRemoteDataSource.loadPhotoById(photoId)?.let { photoMapper.toDomain(it) }

    override fun findAllPhotos(): Flow<List<Photo>> =
        photoRemoteDataSource.loadAllPhotos().map { photoMapper.toDomain(it) }
}