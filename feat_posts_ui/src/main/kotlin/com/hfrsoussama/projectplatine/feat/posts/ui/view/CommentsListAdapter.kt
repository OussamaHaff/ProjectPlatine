package com.hfrsoussama.projectplatine.feat.posts.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.hfrsoussama.projectplatine.feat.posts.core.model.extensions.generateAvatarHttpUrl
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi
import com.hfrsoussama.projectplatine.feat.posts.ui.R
import kotlinx.android.synthetic.main.item_view_comment.view.*

class CommentsListAdapter(
    private val commentsList: List<CommentUi>
) : RecyclerView.Adapter<CommentsListAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view_comment, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun getItemCount() = commentsList.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindToComment(commentsList[position])
    }


    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindToComment(commentUi: CommentUi) {
            itemView.apply {
                tv_comment_author_name.text = commentUi.name
                tv_comment_body.text = commentUi.body
                iv_comment_author_avatar.load(commentUi.generateAvatarHttpUrl()) {
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

}
