package com.famian.githubapp.ui.search

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
import com.famian.githubapp.R
import com.famian.githubapp.databinding.FragmentSearchUserBinding
import com.famian.githubapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SearchUserFragment : BaseFragment<SearchUserViewModel>(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val viewModelFactory: ViewModelProvider.Factory by instance()
    override val viewModel: SearchUserViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SearchUserViewModel::class.java)
    }
    private lateinit var binding: FragmentSearchUserBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search_user, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun bindUI() {
        requireActivity().toolbar.title = "GitHub"
        super.bindUI()
        viewModel.userName.observe(viewLifecycleOwner, Observer { name ->
            view?.let {
                val action = SearchUserFragmentDirections.actionSearchRepoFragmentToUserDetailsFragment(name)
                Navigation.findNavController(it).navigate(action)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //  viewModel = ViewModelProviders.of(this).get(SearchUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
