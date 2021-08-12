package com.leobata.feature_photo.presentation.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.leobata.common_android.base.BaseFragment
import com.leobata.feature_photo.databinding.PhotoListFragmentBinding
import com.leobata.feature_photo.model.Photo
import com.leobata.feature_photo.model.UIResponseState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class PhotoListFragment : BaseFragment<PhotoListFragmentBinding, PhotoListViewModel>(),
    PhotoItemClickListener {
    override var useSharedViewModel = true

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

    private lateinit var listAdapter: PhotoListAdapter

    override fun getViewModelClass() = PhotoListViewModel::class.java

    override fun getViewBinding() = PhotoListFragmentBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        postponeEnterTransition()
        setupUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        viewModel.fetchData()
    }

    private fun setupUI() {
        listAdapter = PhotoListAdapter(arrayListOf(), this)
        listAdapter.setHasStableIds(true)
        binding.photoList.adapter = listAdapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
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