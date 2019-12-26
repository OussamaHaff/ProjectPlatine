package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi

interface CommentRepository {

    suspend fun getAllComments(strategy: CommentRetrievalStrategy): List<CommentUi>

    suspend fun getCommentsByPostId(strategy: MixedSelectiveCommentRetrievalStrategy): List<CommentUi>

}
