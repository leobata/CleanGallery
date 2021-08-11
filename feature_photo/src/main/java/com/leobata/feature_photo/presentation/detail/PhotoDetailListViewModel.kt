package com.leobata.feature_photo.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leobata.domain.usecase.photo.LoadAllPhotos
import com.leobata.feature_photo.mapper.PhotoMapper
import com.leobata.feature_photo.model.UIResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PhotoDetailListViewModel @Inject constructor(
    private val loadAllPhotos: LoadAllPhotos,
    private val photoMapper: PhotoMapper
) : ViewModel() {
    private val _viewState = MutableLiveData<UIResponseState>()
    val viewState: LiveData<UIResponseState> = _viewState

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            loadAllPhotos()
                .onStart { _viewState.value = UIResponseState.Loading }
                .catch { _viewState.value = UIResponseState.Error("Error") }
                .collect {
                    _viewState.value = UIResponseState.Success(content = photoMapper.toView(it))
                }
        }
    }
}