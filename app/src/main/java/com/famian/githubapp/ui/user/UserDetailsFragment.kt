package com.famian.githubapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.famian.githubapp.R
import com.famian.githubapp.databinding.FragmentUserDetailsBinding
import com.famian.githubapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_user_details.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class UserDetailsFragment : BaseFragment<UserDetailsViewModel>(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val viewModelFactory: ViewModelProvider.Factory by instance()
    override val viewModel: UserDetailsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(UserDetailsViewModel::class.java)
    }
    private lateinit var binding: FragmentUserDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user_details, container, false)
        binding.viewmodel = viewModel
        arguments?.let {
            val safeArgs = UserDetailsFragmentArgs.fromBundle(it)
            safeArgs.userName?.let { it1 ->
                viewModel.getUser(it1)
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateTo(R.id.action_userDetailsFragment_to_searchRepoFragment)
            }
        })
    }

    override fun bindUI() {
        viewModel.avatarUrl.observe(viewLifecycleOwner, Observer {
            loadAvatar(it)
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            loading.visibility = View.GONE
            content_main.visibility = View.VISIBLE
        })
        viewModel.toolbarName.observe(viewLifecycleOwner, Observer {
            requireActivity().toolbar.title = "$it profile"
        })
        viewModel.goToList.observe(viewLifecycleOwner, Observer { name ->
            resetToolbar()
            view?.let {
                val action = UserDetailsFragmentDirections.actionUserDetailsFragmentToRepoListFragment(name)
                Navigation.findNavController(it).navigate(action)
            }
        })
        viewModel.goToFollowers.observe(viewLifecycleOwner, Observer { name ->
            resetToolbar()
            view?.let {
                content_main.visibility = View.INVISIBLE
                loading.visibility = View.VISIBLE
                val action = UserDetailsFragmentDirections.actionUserDetailsFragmentToFollowersListFragment(name)
                Navigation.findNavController(it).navigate(action)
                loading.visibility = View.GONE
                content_main.visibility = View.VISIBLE
            }
        })
        super.bindUI()
    }

    private fun loadAvatar(url: String) {
        Glide.with(requireView())
            .load(url)
            .into(imageView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        content_main.visibility = View.INVISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
