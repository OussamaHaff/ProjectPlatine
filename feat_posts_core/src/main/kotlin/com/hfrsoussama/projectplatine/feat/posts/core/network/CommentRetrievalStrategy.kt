package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.hfrsoussama.projectplatine.feat.posts.core.model.extensions.toDbModel
import com.hfrsoussama.projectplatine.feat.posts.core.model.extensions.toUiModel
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi
import com.hfrsoussama.projectplatine.shared.database.dao.CommentDao

interface CommentRetrievalStrategy : RepositoryStrategy {

    suspend fun retrieveComments(
        remoteClient: PostsWebServices,
        commentDao: CommentDao
    ): List<CommentUi>

}

class LocalOnlyCommentRetrievalStrategy : CommentRetrievalStrategy {

    override suspend fun retrieveComments(
        remoteClient: PostsWebServices,
        commentDao: CommentDao

    ): List<CommentUi> = commentDao.getAllCommentsDb().map { toUiModel(it) }

}


class MixedSelectiveCommentRetrievalStrategy(
    private val postId: Long
) : CommentRetrievalStrategy {

    override suspend fun retrieveComments(
        remoteClient: PostsWebServices,
        commentDao: CommentDao
    ): List<CommentUi> {

        remoteClient.getCommentsByPostId(postId)
            .map { it.toDbModel() }
            .forEach { commentDao.insertCommentDb(it) }

        return commentDao.getAllCommentsDbForPost(postId)
            .first()
            .commentsDbList
            .map { toUiModel(it) }
    }

}
