package com.leobata.common_android.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VBinding : ViewBinding, ViewModel : BaseViewModel> : Fragment() {
    open var useSharedViewModel: Boolean = false

    protected lateinit var viewModel: ViewModel
    protected abstract fun getViewModelClass(): Class<ViewModel>

    protected lateinit var binding: VBinding
    protected abstract fun getViewBinding(): VBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigationCommands.observe(viewLifecycleOwner, { command ->
            when (command) {
                is NavigationCommand.Back -> TODO()
                is NavigationCommand.BackTo -> TODO()
                is NavigationCommand.To -> {
                    findNavController().navigate(command.directions)
                }
                is NavigationCommand.ToRoot -> TODO()
            }
        })
    }

    private fun init() {
        binding = getViewBinding()
        viewModel = if (useSharedViewModel) {
            ViewModelProvider(requireActivity()).get(
                getViewModelClass()
            )
        } else {
            ViewModelProvider(this).get(getViewModelClass())
        }
    }
}