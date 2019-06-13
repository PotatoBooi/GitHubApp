package com.famian.githubapp.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.famian.githubapp.R
import com.famian.githubapp.data.model.entity.Repository
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_repo_list.*

class RepoListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recViewRepoList.apply {
            layoutManager = LinearLayoutManager(this@RepoListFragment.context)
            adapter = GroupAdapter<ViewHolder>().apply {
                addAll(listOf(
                    RepoItem(Repository("Type Racer","isiur","Kotlin")),
                    RepoItem(Repository("Type Racer","isiur","Kotlin")),
                    RepoItem(Repository("Type Racer","isiur","Kotlin"))
                )
                )
            }
        }
    }


}
