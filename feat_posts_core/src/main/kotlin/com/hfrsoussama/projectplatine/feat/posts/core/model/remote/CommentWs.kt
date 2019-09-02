package com.hfrsoussama.projectplatine.feat.posts.core.model.remote

data class CommentWs(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)
