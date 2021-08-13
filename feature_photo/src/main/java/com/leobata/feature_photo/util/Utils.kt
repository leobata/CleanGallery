package com.leobata.feature_photo.util

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebSettings
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.leobata.feature_photo.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(android.R.drawable.presence_offline)

    val glideUrl = GlideUrl(
        uri, LazyHeaders.Builder()
            .addHeader("User-Agent", WebSettings.getDefaultUserAgent(context))
            .build()
    )

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(glideUrl)
        .into(this)
}

fun ImageView.startLoadingAnimation() {
    this.apply {
        visibility = View.VISIBLE
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.progress_anim))
    }
}

fun ImageView.stopLoadingAnimation() {
    this.clearAnimation()
    this.visibility = View.GONE
}

