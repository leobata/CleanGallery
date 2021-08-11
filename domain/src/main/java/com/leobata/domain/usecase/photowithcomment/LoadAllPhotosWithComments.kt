package com.leobata.domain.usecase.photowithcomment

import com.leobata.domain.model.PhotoWithComments
import kotlinx.coroutines.flow.Flow

interface LoadAllPhotosWithComments {
    operator fun invoke(): Flow<List<PhotoWithComments>>
}