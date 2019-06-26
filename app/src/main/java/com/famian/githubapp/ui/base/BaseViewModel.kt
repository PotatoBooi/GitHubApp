package com.famian.githubapp.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    protected lateinit var job: Job
    val navigation = SingleLiveEvent<Int>()
    val loading = SingleLiveEvent<Unit>()
    val onError = SingleLiveEvent<String>()
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}