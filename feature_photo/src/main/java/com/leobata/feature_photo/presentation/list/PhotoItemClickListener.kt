package com.leobata.feature_photo.presentation.list

import android.view.View
import com.leobata.feature_photo.model.Photo

interface PhotoItemClickListener {
    fun onPhotoItemClicked(photo: Photo, view: View)
}