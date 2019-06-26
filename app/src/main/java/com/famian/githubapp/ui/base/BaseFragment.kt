package com.famian.githubapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

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
        viewModel?.onError?.observe(viewLifecycleOwner, Observer {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
        })
    }

    open fun navigateTo(@IdRes action: Int) {
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

    open fun resetToolbar() {
        requireActivity().toolbar.title = ""
    }
}