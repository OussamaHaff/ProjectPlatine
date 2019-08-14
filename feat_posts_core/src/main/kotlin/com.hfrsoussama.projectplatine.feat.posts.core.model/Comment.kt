package com.hfrsoussama.projectplatine.feat.posts.core.model

data class Comment(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)
