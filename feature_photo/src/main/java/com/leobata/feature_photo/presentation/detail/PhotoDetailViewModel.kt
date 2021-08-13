package com.leobata.feature_photo.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leobata.domain.usecase.comment.LoadComments
import com.leobata.feature_photo.mapper.CommentMapper
import com.leobata.feature_photo.model.Photo
import com.leobata.feature_photo.model.PhotoWithComments
import com.leobata.feature_photo.model.UIResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PhotoDetailViewModel @Inject constructor(
    private val loadComments: LoadComments,
    private val commentMapper: CommentMapper
) : ViewModel() {
    private val _viewState = MutableLiveData<UIResponseState>()
    val viewState: LiveData<UIResponseState> = _viewState

    private lateinit var _photo: Photo
    fun setArguments(photo: Photo) {
        _photo = photo
    }

    fun fetchData() {
        viewModelScope.launch {
            loadComments(_photo.id)
                .onStart { _viewState.value = UIResponseState.Loading }
                .catch { _viewState.value = UIResponseState.Error("Error") }
                .collect {
                    _viewState.value = UIResponseState.Success(
                        content = PhotoWithComments(
                            _photo,
                            commentMapper.toView(it)
                        )
                    )
                }
        }
    }
}