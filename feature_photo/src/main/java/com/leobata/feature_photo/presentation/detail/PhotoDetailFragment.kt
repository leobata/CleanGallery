package com.leobata.feature_photo.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.leobata.common_android.base.BaseFragment
import com.leobata.feature_photo.databinding.PhotoDetailFragmentBinding
import com.leobata.feature_photo.model.Photo
import com.leobata.feature_photo.model.UIResponseState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class PhotoDetailFragment(private val photo: Photo) :
    BaseFragment<PhotoDetailFragmentBinding, PhotoDetailViewModel>() {

    private val viewStateObserver = Observer<UIResponseState> {
        when (it) {

            is UIResponseState.Error -> Log.d("PhotoDetailFragment", "ERROR")
            is UIResponseState.Loading -> Log.d("PhotoDetailFragment", "LOADING")
            is UIResponseState.Success<*> -> {
            }
        }
        binding.expandedImage.transitionName = photo.id.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        viewModel.fetchData(photo)
    }

    override fun getViewModelClass() = PhotoDetailViewModel::class.java

    override fun getViewBinding() = PhotoDetailFragmentBinding.inflate(layoutInflater)
}