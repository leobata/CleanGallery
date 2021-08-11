package com.leobata.data.local.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_table")
data class Photo(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    val id: Long = 0,
    val albumId: Long = 0,
    val title: String = "",
    val url: String = "",
    val thumbnailUrl: String = ""
)
