package com.hfrsoussama.projectplatine.feat.posts.core.model.presentation

data class CommentUi(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)
