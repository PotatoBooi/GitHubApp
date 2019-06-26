package com.famian.githubapp.ui.search

import androidx.databinding.ObservableField
import com.famian.githubapp.ui.base.BaseViewModel
import com.famian.githubapp.ui.base.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class SearchUserViewModel : BaseViewModel() {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    init {
        job = Job()
    }
    var searchText = ObservableField<String>("")
    val userName: SingleLiveEvent<String> = SingleLiveEvent()
    fun searchClick() {
        if (searchText.get().isNullOrEmpty()) return

        userName.postValue(searchText.get())
    }
}
