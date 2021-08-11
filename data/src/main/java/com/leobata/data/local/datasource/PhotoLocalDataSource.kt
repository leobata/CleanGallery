package com.leobata.data.local.datasource

import com.leobata.data.local.dao.PhotoDao
import com.leobata.data.local.mapper.PhotoMapper
import com.leobata.data.repository.datasource.PhotoDataSource
import com.leobata.data.repository.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun loadPhotoById(photoId: Long): Photo? =
        photoDao.getPhotoById(photoId)?.let { photoMapper.toRepo(it) }
}