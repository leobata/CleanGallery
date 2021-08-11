package com.leobata.common_android.base

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections

abstract class BaseViewModel : ViewModel() {
    val navigationCommands = SingleLiveEvent<NavigationCommand>()

    fun navigate(directions: NavDirections) {
        navigationCommands.postValue(NavigationCommand.To(directions))
    }
}