package com.famian.githubapp.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val navigation = SingleLiveEvent<Int>()
}