package com.famian.githubapp.ui.list

import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.famian.githubapp.R
import com.famian.githubapp.data.model.entity.UserSimple
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.followers_list_item.*

class FollowerItem(private val user: UserSimple, private val onClickListener: OnListItemClickListener) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            itemView.setOnClickListener {
                onClickListener.onClick(position)
            }
            textViewName.text = user.login
            Glide.with(viewHolder.containerView)
                .load(user.avatarUrl)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }


                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        avatarProgressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(imageViewFollowerAvatar)
        }
    }

    override fun getLayout(): Int = R.layout.followers_list_item
}