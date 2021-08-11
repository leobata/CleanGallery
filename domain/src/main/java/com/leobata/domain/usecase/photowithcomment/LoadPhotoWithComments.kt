package com.leobata.domain.usecase.photowithcomment

import com.leobata.domain.model.PhotoWithComments
import kotlinx.coroutines.flow.Flow

interface LoadPhotoWithComments {
    operator fun invoke(photoId: Long): Flow<PhotoWithComments?>
}