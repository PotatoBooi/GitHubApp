package com.famian.githubapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.famian.githubapp.R
import com.famian.githubapp.databinding.FragmentSearchRepoBinding
import com.famian.githubapp.ui.base.BaseFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SearchRepoFragment : BaseFragment<SearchRepoViewModel>(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val viewModelFactory: ViewModelProvider.Factory by instance()
    override val viewModel: SearchRepoViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SearchRepoViewModel::class.java)
    }
    private lateinit var binding: FragmentSearchRepoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search_repo, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //  viewModel = ViewModelProviders.of(this).get(SearchRepoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
