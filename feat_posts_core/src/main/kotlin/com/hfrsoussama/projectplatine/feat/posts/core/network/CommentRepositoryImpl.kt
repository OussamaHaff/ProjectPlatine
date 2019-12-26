package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi
import com.hfrsoussama.projectplatine.shared.database.dao.CommentDao

class CommentRepositoryImpl(
    private val remoteClient: PostsWebServices,
    private val commentDao: CommentDao
) : CommentRepository {

    override suspend fun getAllComments(strategy: CommentRetrievalStrategy) =
        strategy.retrieveComments(remoteClient, commentDao)


    override suspend fun getCommentsByPostId(
        strategy: MixedSelectiveCommentRetrievalStrategy
    ): List<CommentUi> = strategy.retrieveComments(remoteClient, commentDao)

}
