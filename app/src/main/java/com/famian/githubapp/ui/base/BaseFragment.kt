package com.famian.githubapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

abstract class BaseFragment<T : BaseViewModel> : Fragment() {
    open val viewModel: T? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    open fun bindUI() {
        viewModel?.navigation?.observe(viewLifecycleOwner, Observer {
            navigateTo(it)
        })
    }

    open fun navigateTo(@IdRes action: Int) {
        view?.let { Navigation.findNavController(it).navigate(action) }
    }
}