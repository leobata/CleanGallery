package com.leobata.feature_photo.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codeboy.pager2_transformers.Pager2_CubeOutTransformer
import com.leobata.common_android.base.BaseFragment
import com.leobata.feature_photo.R
import com.leobata.feature_photo.databinding.PhotoDetailListFragmentBinding
import com.leobata.feature_photo.model.Photo
import com.leobata.feature_photo.model.UIResponseState
import com.leobata.feature_photo.presentation.list.PhotoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class PhotoDetailListFragment :
    BaseFragment<PhotoDetailListFragmentBinding, PhotoListViewModel>() {
    override var useSharedViewModel = true
    private val args: PhotoDetailListFragmentArgs by navArgs()

    private val viewStateObserver = Observer<UIResponseState> {
        when (it) {
            is UIResponseState.Error -> Log.d("PhotoDetailListFragment", "ERROR")
            is UIResponseState.Loading -> Log.d("PhotoDetailListFragment", "LOADING")
            is UIResponseState.Success<*> -> renderDetails(it.content as List<Photo>)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition =
            TransitionInflater.from(context)
                .inflateTransition(R.transition.image_shared_element_transition)
        postponeEnterTransition()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun renderDetails(photoList: List<Photo>) {
        Log.d("BATATA", "Photo ID = ${args.photoId}")
        val position = photoList.indexOfFirst { it.id == args.photoId }
        binding.pager.adapter = ScreenSlidePagerAdapter(requireActivity(), photoList)
        binding.pager.setPageTransformer(Pager2_CubeOutTransformer())
        binding.pager.post {
            binding.pager.setCurrentItem(position, false)
            binding.pager.transitionName = args.photoId.toString()
            prepareTransitions(position)
            startPostponedEnterTransition()
        }
    }

    private fun prepareTransitions(position: Int) {
        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                binding.pager.findViewWithTag<ViewGroup>(position)
                    ?.findViewById<ImageView>(R.id.expandedImage)
                    ?.let { sharedElements[names[0]] = it }
            }
        })
    }

    private inner class ScreenSlidePagerAdapter(
        fa: FragmentActivity,
        private val pages: List<Photo>
    ) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = pages.size

        override fun createFragment(position: Int): Fragment = PhotoDetailFragment(pages[position])
    }

    override fun getViewModelClass() = PhotoListViewModel::class.java

    override fun getViewBinding() = PhotoDetailListFragmentBinding.inflate(layoutInflater)
}