package com.leobata.data.remote.model

import com.squareup.moshi.Json

data class Comment(
    @Json(name = "postId")
    val postId: Long = 0,
    @Json(name = "id")
    val id: Long = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "body")
    val body: String = ""
)
