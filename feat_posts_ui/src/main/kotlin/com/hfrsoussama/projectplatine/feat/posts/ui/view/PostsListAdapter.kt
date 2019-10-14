package com.hfrsoussama.projectplatine.feat.posts.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.feat.posts.ui.R
import kotlinx.android.synthetic.main.item_view_post.view.*

class PostsListAdapter(
    private val postsList: List<PostUi>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<PostsListAdapter.PostViewHolder>() {

    companion object {
        private const val ITEM_ELEVATION = 16f
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view_post, parent, false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount() = postsList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindToPost(postsList[position], listener)
    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindToPost(post: PostUi, listener: OnItemClickListener) {
            itemView.apply {
                tv_post_title.text = post.title
                tv_post_body.text = post.body
                tv_user_name.text = post.userId.toString()

                elevation = ITEM_ELEVATION * itemView.context.resources.displayMetrics.density

                setOnClickListener { listener.onItemClick(post) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(post: PostUi)
    }

}
