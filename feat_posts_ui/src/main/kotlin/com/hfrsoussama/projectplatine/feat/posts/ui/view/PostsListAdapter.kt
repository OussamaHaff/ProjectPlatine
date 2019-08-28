package com.hfrsoussama.projectplatine.feat.posts.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.feat.posts.ui.R
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.PostWs
import kotlinx.android.synthetic.main.item_view_post.view.*

class PostsListAdapter(
    private val postsList: List<PostUi>,
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


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindToPost(post: PostUi, listener: OnItemClickListener) {
            itemView.tv_post_title.text = post.title
            itemView.tv_post_body.text = post.body
            itemView.tv_user_name.text = post.userId.toString()
            itemView.setOnClickListener { listener.onItemClick(post) }

            val cornerRadius : Float = (itemView as MaterialCardView).radius
            val customOutlineProvider = CustomOutlineProvider(
                cornerRadius = cornerRadius,
                scaleX = 1f,
                scaleY = 1f,
                yShift = 0
            )
            itemView.outlineProvider = customOutlineProvider
            itemView.elevation = 16 * itemView.context.resources.displayMetrics.density
        }
    }

    interface OnItemClickListener {
        fun onItemClick(post: PostUi)
    }

}
