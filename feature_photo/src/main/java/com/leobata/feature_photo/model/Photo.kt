package com.leobata.feature_photo.model

/**
 * Data class to represent a photo
 *
 * @param id photo id
 * @param title photo title
 * @param url photo url
 * @param thumbnailUrl photo thumbnail url
 */
data class Photo(
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
