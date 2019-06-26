package com.famian.githubapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.famian.githubapp.R
import com.famian.githubapp.databinding.FragmentFollowerListBinding
import com.famian.githubapp.ui.base.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_follower_list.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class FollowersListFragment : BaseFragment<FollowersListViewModel>(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val viewModelFactory: ViewModelProvider.Factory by instance()
    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    private lateinit var binding: FragmentFollowerListBinding
    override val viewModel: FollowersListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(FollowersListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follower_list, container, false)
        binding.viewmodel = viewModel
        groupAdapter = GroupAdapter()
        return binding.root
    }

    override fun bindUI() {
        super.bindUI()
        viewModel.followers.observe(viewLifecycleOwner, Observer { list ->
            groupAdapter.updateAsync(list.map { FollowerItem(it, viewModel.onListItemClickListener) })
        })
        viewModel.openItem.observe(viewLifecycleOwner, Observer { name ->
            resetToolbar()
            view?.let {
                val action = FollowersListFragmentDirections.actionFollowersListFragmentToUserDetailsFragment(name)
                Navigation.findNavController(it).navigate(action)
            }
        })
        recViewFollowerList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = groupAdapter
        }
        arguments?.let { bundle ->
            val safeArgs = FollowersListFragmentArgs.fromBundle(bundle)
            safeArgs.username.let {
                viewModel.loadFollowers(it)
                requireActivity().toolbar.title = "$it followers"
            }
        }

    }

}