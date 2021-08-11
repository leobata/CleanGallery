package com.leobata.data.remote.datasource

import com.leobata.data.remote.api.ApiHelper
import com.leobata.data.remote.mapper.PhotoMapper
import com.leobata.data.repository.datasource.PhotoDataSource
import com.leobata.data.repository.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class PhotoRemoteDataSource @Inject constructor(
    private val apiHelper: ApiHelper,
    private val photoMapper: PhotoMapper
) : PhotoDataSource {

    override fun loadAllPhotos(): Flow<List<Photo>> {
        return flow {
            var photos = listOf<Photo>()
            apiHelper.getAllPhotos().body()?.let {
                photos = it.map { photo -> photoMapper.toRepo(photo) }
            }
            emit(photos)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun loadPhotoById(photoId: Long): Photo? {
        var photo: Photo? = null
        loadAllPhotos().collect {
            photo = it.first { photo -> photo.id == photoId }
        }
        return photo
    }
}