package com.leobata.feature_photo.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.leobata.feature_photo.R
import com.leobata.feature_photo.databinding.PhotoDetailFragmentBinding
import com.leobata.feature_photo.model.Photo
import com.leobata.feature_photo.model.UIResponseState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailFragment(private val photo: Photo) : Fragment() {
    private var _binding: PhotoDetailFragmentBinding? = null
    private val binding get() = _binding
    private val viewModel: PhotoDetailViewModel by viewModels()

    private val commentAdapter = CommentListAdapter(arrayListOf())

    private val viewStateObserver = Observer<UIResponseState> {
        when (it) {
            is UIResponseState.Error -> Log.d("PhotoListFragment", "ERROR")
            is UIResponseState.Loading -> Log.d("PhotoListFragment", "LOADING")
            is UIResponseState.Success<*> -> {
                Log.d("BATATA", "CHEGOU")
                binding?.expandedImage?.transitionName = photo.id.toString()
//                prepareTransitions()
//                startPostponedEnterTransition()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PhotoDetailFragmentBinding.inflate(inflater, container, false)
//        _binding?.expandedImage?.transitionName = photo.id.toString()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner
//        setupToolBar()
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        viewModel.fetchData(photo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun setupToolBar() {
////        binding?.toolbar?.setOnMenuItemClickListener { item ->
////            Log.d("BATATA", "Item ID = ${item.itemId}")
////            Log.d("BATATA", "Home ID = ${android.R.id.home}")
////            if (item.itemId == android.R.id.home) {
////                findNavController().popBackStack()
////            }
////            false
////        }
//        activity?.setActionBar(binding?.toolbar)
//        binding?.toolbar?.setNavigationIcon(android.R.drawable.ic_delete)
//        binding?.toolbar?.setNavigationOnClickListener {
//            Log.d("BATATA", "BACK!!!!")
//        }
//        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
//        activity?.actionBar?.setDisplayShowHomeEnabled(true)
//    }
}