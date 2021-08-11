package com.leobata.data.remote.model

import com.squareup.moshi.Json

data class Photo(
    @Json(name = "albumId")
    val albumId: Long = 0,
    @Json(name = "id")
    val id: Long = 0,
    @Json(name = "title")
    val title: String = "",
    @Json(name = "url")
    val url: String = "",
    @Json(name = "thumbnailUrl")
    val thumbnailUrl: String = ""
)
