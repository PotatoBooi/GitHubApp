package com.famian.githubapp

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.famian.githubapp.ui.base.KodeinViewModelFactory
import com.famian.githubapp.ui.search.SearchRepoViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class GitHubApp : Application(), KodeinAware {
    override val kodein: Kodein by Kodein.lazy {
        import(androidXModule(this@GitHubApp))
        bind<ViewModelProvider.Factory>() with singleton { KodeinViewModelFactory(kodein) }
        bind<SearchRepoViewModel>() with provider { SearchRepoViewModel() }
    }
}