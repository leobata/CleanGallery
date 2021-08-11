package com.leobata.domain.model

data class PhotoWithComments(
    val photo: Photo,
    val comments: List<Comment>
)
