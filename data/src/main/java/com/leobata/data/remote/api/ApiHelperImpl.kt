package com.leobata.data.remote.api

import com.leobata.data.remote.model.Comment
import com.leobata.data.remote.model.Photo
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllPhotos(): Response<List<Photo>> = apiService.getAllPhotos()

    override suspend fun getAllPhotoComments(photoId: Long): Response<List<Comment>> =
        apiService.getAllPhotoComments(photoId)
}