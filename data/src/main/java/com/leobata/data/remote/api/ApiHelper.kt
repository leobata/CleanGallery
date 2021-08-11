package com.leobata.data.remote.api

import com.leobata.data.remote.model.Comment
import com.leobata.data.remote.model.Photo
import retrofit2.Response

interface ApiHelper {
    suspend fun getAllPhotos(): Response<List<Photo>>
    suspend fun getAllPhotoComments(photoId: Long): Response<List<Comment>>
}