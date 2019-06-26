package com.famian.githubapp

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.famian.githubapp.data.dataSource.GitHubApiService
import com.famian.githubapp.data.dataSource.GitHubDataSource
import com.famian.githubapp.data.dataSource.network.ConnectivityInterceptor
import com.famian.githubapp.ui.base.KodeinViewModelFactory
import com.famian.githubapp.ui.list.FollowersListViewModel
import com.famian.githubapp.ui.list.RepoListViewModel
import com.famian.githubapp.ui.search.SearchUserViewModel
import com.famian.githubapp.ui.user.UserDetailsViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class GitHubApp : Application(), KodeinAware {
    override val kodein: Kodein by Kodein.lazy {
        import(androidXModule(this@GitHubApp))
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptor(instance()) }
        bind() from singleton { GitHubApiService(instance()) }
        bind() from singleton { GitHubDataSource(instance()) }
        bind<ViewModelProvider.Factory>() with singleton { KodeinViewModelFactory(kodein) }
        bind<SearchUserViewModel>() with provider { SearchUserViewModel() }
        bind<UserDetailsViewModel>() with provider { UserDetailsViewModel(instance()) }
        bind<RepoListViewModel>() with provider { RepoListViewModel(instance()) }
        bind<FollowersListViewModel>() with provider { FollowersListViewModel(instance()) }
    }
}