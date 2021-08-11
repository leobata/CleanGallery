package com.leobata.feature_photo.model

/**
 * Data class to represent a photo with comments
 *
 * @param photo the photo data
 * @param comments photo comments list
 */
data class PhotoWithComments(
    val photo: Photo,
    val comments: List<Comment>
)
