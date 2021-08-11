package com.leobata.data.remote.api

import com.leobata.data.remote.model.Comment
import com.leobata.data.remote.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("photos")
    suspend fun getAllPhotos(): Response<List<Photo>>

    @GET("photos/{photoId}/comments")
    suspend fun getAllPhotoComments(
        @Path("photoId") photoId: Long): Response<List<Comment>>
}