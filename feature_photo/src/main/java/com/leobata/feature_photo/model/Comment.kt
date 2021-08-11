package com.leobata.feature_photo.model

/**
 * Data class to represent a comment
 *
 * @param id comment id
 * @param name author name
 * @param email author email
 * @param body comment body
 */
data class Comment(
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)
