package com.famian.githubapp.ui.user

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.famian.githubapp.R
import com.famian.githubapp.data.dataSource.GitHubDataSource
import com.famian.githubapp.data.model.entity.User
import com.famian.githubapp.ui.base.BaseViewModel
import com.famian.githubapp.ui.base.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

class UserDetailsViewModel(private val gitHubDataSource: GitHubDataSource) : BaseViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    init {
        job = Job()
    }

    val goToList = SingleLiveEvent<String>()
    val goToFollowers = SingleLiveEvent<String>()
    val userName = ObservableField<String>("")
    val userDetails = ObservableField<String>("")
    val userLocation = ObservableField<String>("")
    val userCompany = ObservableField<String>("")
    val userRepos = ObservableField<String>("")
    val userFollowers = ObservableField<String>("")
    val userFollowing = ObservableField<String>("")
    val toolbarName = SingleLiveEvent<String>()
    val avatarUrl = SingleLiveEvent<String>()
    private val currentUser = MutableLiveData<User>()
    fun getUser(userName: String) {
        loadUser(userName)
    }

    fun reposClick() {
        currentUser.value?.let {
            toolbarName.postValue("")
            goToList.postValue(it.login)
        }
    }

    fun followersClick() {
        currentUser.value?.let {
            toolbarName.postValue("")
            goToFollowers.postValue(it.login)
        }
    }

    private fun loadUser(username: String) = launch {
        try {
            val data = gitHubDataSource.getUser(username)
            currentUser.postValue(data)
            userName.set(data.name)
            userDetails.set(data.description)
            userLocation.set(data.location ?: "none")
            userCompany.set(data.company ?: "none")
            userFollowers.set(data.followers.toString())
            userRepos.set(data.repos.toString())
            userFollowing.set(data.following.toString())
            avatarUrl.postValue(data.avatarUrl)
            toolbarName.postValue(data.login)
            loading.postValue(Unit)


        } catch (ex: Exception) {
            when (ex) {
                is HttpException -> {
                    onError.postValue("Connection error: ${ex.response().message()}")
                    navigation.postValue(R.id.action_userDetailsFragment_to_searchRepoFragment)
                }
                else -> {
                    onError.postValue("Error: ${ex.message}")
                    navigation.postValue(R.id.action_userDetailsFragment_to_searchRepoFragment)
                }
            }
        }
    }
}
