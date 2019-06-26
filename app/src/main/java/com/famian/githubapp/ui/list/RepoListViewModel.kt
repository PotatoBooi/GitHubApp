package com.famian.githubapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.famian.githubapp.data.dataSource.GitHubDataSource
import com.famian.githubapp.data.model.entity.toRepositoryModel
import com.famian.githubapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class RepoListViewModel(private val gitHubDataSource: GitHubDataSource) : BaseViewModel() {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    init {
        job = Job()
    }

    val repos: LiveData<List<RepoItem>>
        get() = _repos
    private val _repos = MutableLiveData<List<RepoItem>>()
    fun loadUserRepos(username: String) {
        val data = runBlocking { gitHubDataSource.getUserRepos(username) }
        val map = data.map { it.toRepositoryModel }
        _repos.postValue(map.map { RepoItem(it) })
    }

}
