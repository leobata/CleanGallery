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
internal class PhotoDetailFragment :
    BaseFragment<PhotoDetailFragmentBinding, PhotoDetailViewModel>() {
    private var photo: Photo? = null

    private val viewStateObserver = Observer<UIResponseState> {
        when (it) {
            is UIResponseState.Error -> Log.d("PhotoDetailFragment", "ERROR")
            is UIResponseState.Loading -> Log.d("PhotoDetailFragment", "LOADING")
            is UIResponseState.Success<*> -> {
                binding.expandedImage.transitionName = photo?.id.toString()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photo = arguments?.getParcelable<Photo>(ARG_PHOTO)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        photo?.let { viewModel.setArguments(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.fetchData()
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    override fun onResume() {
        super.onResume()
        if (isStateSaved) {
            viewModel.fetchData()
        }
    }

    override fun getViewModelClass() = PhotoDetailViewModel::class.java

    override fun getViewBinding() = PhotoDetailFragmentBinding.inflate(layoutInflater)

    companion object {
        private val ARG_PHOTO = "photo_arg"

        fun newInstance(photo: Photo): PhotoDetailFragment {
            val args: Bundle = Bundle()
            args.putParcelable(ARG_PHOTO, photo)
            val fragment = PhotoDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}