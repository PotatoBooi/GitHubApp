package com.famian.githubapp.ui.search

import androidx.databinding.ObservableField
import com.famian.githubapp.R
import com.famian.githubapp.ui.base.BaseViewModel

class SearchRepoViewModel : BaseViewModel() {
    var searchText = ObservableField<String>("")
    fun searchClick() {
        navigation.postValue(R.id.action_searchRepoFragment_to_repoListFragment)
    }
}
