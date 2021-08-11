package com.leobata.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class PhotoWithComments(
    @Embedded
    val photo: Photo?,
    @Relation(
        parentColumn = "id",
        entityColumn = "photoId"
    )
    val commentList: List<Comment>?
)
