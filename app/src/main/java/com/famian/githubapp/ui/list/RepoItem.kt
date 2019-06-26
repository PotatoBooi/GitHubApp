package com.famian.githubapp.ui.list

import com.famian.githubapp.R
import com.famian.githubapp.data.model.entity.Repository
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.repo_list_item.*

class RepoItem(private val repo: Repository) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textViewRepoTitle.text = repo.title
            textViewLanguage.text = repo.language
            textViewStars.text = repo.stars.toString()
        }
    }

    override fun getLayout(): Int = R.layout.repo_list_item
}
