package com.famian.githubapp.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.famian.githubapp.R
import com.famian.githubapp.databinding.FragmentRepoListBinding
import com.famian.githubapp.ui.base.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class RepoListFragment : BaseFragment<RepoListViewModel>(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val viewModelFactory: ViewModelProvider.Factory by instance()
    private lateinit var binding: FragmentRepoListBinding
    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    override val viewModel: RepoListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(RepoListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_list, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun bindUI() {
        super.bindUI()
        progressBarRepoList.visibility = View.VISIBLE
        recViewRepoList.visibility = View.INVISIBLE
        viewModel.repos.observe(viewLifecycleOwner, Observer {
            groupAdapter.updateAsync(it)
            progressBarRepoList.visibility = View.GONE
            recViewRepoList.visibility = View.VISIBLE
        })

        arguments?.let {
            val safeArgs = RepoListFragmentArgs.fromBundle(it)
            safeArgs.username?.let { it1 ->
                viewModel?.loadUserRepos(it1)
                requireActivity().toolbar.title = "$it1 repos"
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        groupAdapter = GroupAdapter<ViewHolder>()
        recViewRepoList.apply {
            layoutManager = LinearLayoutManager(this@RepoListFragment.context)
            adapter = groupAdapter
        }

    }


}

interface OnListItemClickListener {
    fun onClick(position: Int)
}
