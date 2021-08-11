package com.leobata.domain.usecase.comment

import com.leobata.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface LoadComments {
    operator fun invoke(photoId: Long): Flow<List<Comment>>
}