package com.leobata.data.repository

import android.app.Application
import com.leobata.data.local.di.LocalPhotoDataSource
import com.leobata.data.local.preferences.PreferencesHelper
import com.leobata.data.remote.di.RemotePhotoDataSource
import com.leobata.data.repository.datasource.PhotoDataSource
import com.leobata.data.repository.mapper.PhotoMapper
import com.leobata.domain.model.Photo
import com.leobata.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

internal class PhotoRepositoryImpl @Inject constructor(
    @RemotePhotoDataSource private val photoRemoteDataSource: PhotoDataSource,
    @LocalPhotoDataSource private val photoLocalDataSource: PhotoDataSource,
    private val photoMapper: PhotoMapper,
    private val application: Application
) : PhotoRepository {
    override suspend fun findPhotoById(photoId: Long): Photo? =
        photoRemoteDataSource.loadPhotoById(photoId)?.let { photoMapper.toDomain(it) }

    override fun findAllPhotos(): Flow<List<Photo>> {
        return flow {
            photoRemoteDataSource.loadAllPhotos()
                .catch {
                    if (!PreferencesHelper.isOfflineDataExpired(application)) {
                        photoLocalDataSource.loadAllPhotos().collect { localPhotos ->
                            if (localPhotos.isNotEmpty()) {
                                emit(photoMapper.toDomain(localPhotos))
                            } else {
                                throw Exception("No values found")
                            }
                        }
                    } else {
                        throw Exception("No values found")
                    }
                }
                .collect { remotePhotos ->
                    PreferencesHelper.setLastSyncTime(application)
                    photoLocalDataSource.addAllPhotos(remotePhotos)
                    photoLocalDataSource.loadAllPhotos().collect { localPhotos ->
                        emit(photoMapper.toDomain(localPhotos))
                    }
                }
        }.flowOn(Dispatchers.IO)
    }
}