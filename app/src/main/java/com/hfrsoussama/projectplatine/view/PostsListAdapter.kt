package com.hfrsoussama.projectplatine.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfrsoussama.projectplatine.R
import com.hfrsoussama.projectplatine.model.Post
import kotlinx.android.synthetic.main.item_view_post.view.*

class PostsListAdapter(
    private val postsList: List<Post>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<PostsListAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view_post, parent, false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount() = postsList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindToPost(postsList[position], listener)
    }


    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindToPost(post: Post, listener: OnItemClickListener) {
            itemView.tv_post_title.text = post.title
            itemView.setOnClickListener { listener.onItemClick(post) }
        }
    }

    interface OnItemClickListener  {
        fun onItemClick(post: Post)
    }

}
