package com.leobata.feature_photo.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.leobata.feature_photo.model.PhotoWithComments
import com.leobata.feature_photo.model.UIResponseState
import com.leobata.feature_photo.presentation.detail.CommentListAdapter

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    view.loadImage(url, getProgressDrawable(view.context))
}

@BindingAdapter("setRefreshing")
fun updateRefreshing(swipeRefreshLayout: SwipeRefreshLayout, state: UIResponseState) {
    swipeRefreshLayout.isRefreshing = state is UIResponseState.Loading
}

@BindingAdapter("setTitle")
fun CollapsingToolbarLayout.setTitle(state: UIResponseState?) {
    if (isInstanceOf<UIResponseState.Success<PhotoWithComments>>(state)) {
        val data = state as UIResponseState.Success<PhotoWithComments>
        title = data.content.photo.title
    }
}

@BindingAdapter("loadFromUrl")
fun ImageView.loadFromUrl(state: UIResponseState?) {
    if (isInstanceOf<UIResponseState.Success<PhotoWithComments>>(state)) {
        val data = state as UIResponseState.Success<PhotoWithComments>
        loadImage(data.content.photo.url, getProgressDrawable(context))
    }
}

@BindingAdapter("comments")
fun RecyclerView.setComments(state: UIResponseState?) {
    adapter = if (isInstanceOf<UIResponseState.Success<PhotoWithComments>>(state)) {
        val data = state as UIResponseState.Success<PhotoWithComments>
        val adapter = CommentListAdapter(ArrayList(data.content.comments))
        adapter.setHasStableIds(true)
        adapter
    } else {
        CommentListAdapter(arrayListOf())
    }
    layoutManager = LinearLayoutManager(context)
}

@BindingAdapter("setVisibility")
fun RecyclerView.setVisibility(state: UIResponseState?) {
    visibility = if (isInstanceOf<UIResponseState.Error>(state) ||
        isInstanceOf<UIResponseState.Loading>(state)
    ) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("errorVisibility")
fun TextView.errorVisibility(state: UIResponseState?) {
    visibility = if (isInstanceOf<UIResponseState.Error>(state)) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("setLoading")
fun ImageView.setLoading(state: UIResponseState?) {
    if (isInstanceOf<UIResponseState.Loading>(state)) {
        startLoadingAnimation()
    } else {
        stopLoadingAnimation()
    }
}

inline fun <reified T> isInstanceOf(instance: Any?): Boolean {
    return instance is T
}