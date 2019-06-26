package com.famian.githubapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.famian.githubapp.data.dataSource.GitHubDataSource
import com.famian.githubapp.data.model.entity.UserSimple
import com.famian.githubapp.ui.base.BaseViewModel
import com.famian.githubapp.ui.base.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FollowersListViewModel(private val gitHubDataSource: GitHubDataSource) : BaseViewModel() {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    init {
        job = Job()
    }

    val followers: LiveData<List<UserSimple>>
        get() = _followers
    private val _followers = MutableLiveData<List<UserSimple>>()
    val onListItemClickListener = object : OnListItemClickListener {
        override fun onClick(position: Int) {
            followers.value?.let {
                openItem.postValue(it[position].login)
            }

        }
    }
    val openItem = SingleLiveEvent<String>()
    fun loadFollowers(username: String) {
        launch {
            val data = gitHubDataSource.getUserFollowers(username)
            _followers.postValue(data)
        }
    }
}