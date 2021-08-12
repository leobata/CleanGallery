package com.leobata.data.local.datasource

import com.leobata.data.local.dao.PhotoDao
import com.leobata.data.local.mapper.PhotoMapper
import com.leobata.data.repository.datasource.PhotoDataSource
import com.leobata.data.repository.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class PhotoLocalDataSource @Inject constructor(
    private val photoDao: PhotoDao,
    private val photoMapper: PhotoMapper
) : PhotoDataSource {
    override fun loadAllPhotos(): Flow<List<Photo>> {
        return flow {
            photoDao.getPhotos().collect {
                emit(it.map { localPhoto -> photoMapper.toRepo(localPhoto) })
            }
        }
    }

    override suspend fun loadPhotoById(photoId: Long): Photo? =
        photoDao.getPhotoById(photoId)?.let { photoMapper.toRepo(it) }

    override suspend fun addPhoto(photo: Photo) {
        photoDao.insertPhoto(photoMapper.toLocal(photo))
    }

    override suspend fun addAllPhotos(photoList: List<Photo>) {
        photoDao.deleteAllPhotos()
        photoDao.insertAllPhotos(photoMapper.toLocal(photoList))
    }

    override suspend fun removePhoto(photoId: Long) {
        photoDao.deletePhotoById(photoId)
    }

    override suspend fun removeAllPhotos() {
        photoDao.deleteAllPhotos()
    }
}