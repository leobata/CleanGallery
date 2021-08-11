package com.leobata.feature_photo.presentation.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.leobata.feature_photo.databinding.PhotoListFragmentBinding
import com.leobata.feature_photo.model.Photo
import com.leobata.feature_photo.model.UIResponseState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListFragment : Fragment(), PhotoItemClickListener {
    private val viewStateObserver = Observer<UIResponseState> {
        when (it) {
            is UIResponseState.Error -> Log.d("PhotoListFragment", "ERROR")
            is UIResponseState.Loading -> Log.d("PhotoListFragment", "LOADING")
            is UIResponseState.Success<*> -> {
                listAdapter.updatePhotoList(it.content as List<Photo>)
                startPostponedEnterTransition()
            }
        }
    }
    private val viewModel: PhotoListViewModel by viewModels()
    private val listAdapter =
        PhotoListAdapter(
            arrayListOf(),
            this
        )

    private var _binding: PhotoListFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PhotoListFragmentBinding.inflate(inflater, container, false)
        postponeEnterTransition()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun setupUI() {
        binding?.photoList?.adapter = listAdapter
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPhotoItemClicked(photo: Photo, view: View) {
        val extras = FragmentNavigatorExtras(view to photo.id.toString())
        prepareTransitions(view)
        findNavController().navigate(
            PhotoListFragmentDirections.actionPhotoListToPhotoDetail(photo.id),
            extras
        )
    }

    private fun prepareTransitions(view: View) {
        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                sharedElements[names[0]] = view
            }
        })
    }
}