package com.leobata.data.local.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment_table")
data class Comment(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    val id: Long = 0,
    val postId: Long = 0,
    val photoId: Long = 0,
    val name: String = "",
    val email: String = "",
    val body: String = ""
)
