package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.hfrsoussama.projectplatine.feat.posts.core.model.extensions.toUiModel
import com.hfrsoussama.projectplatine.feat.posts.core.model.extensions.toDbModel
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.shared.database.dao.PostDao

interface PostRetrievalStrategy : RepositoryStrategy {
    suspend fun retrievePosts(client: PostsWebServices, postDao: PostDao): List<PostUi>
}

/**
 * The default strategy is to get posts from remote server, store them in local database,
 *  and then retrieve them from database
 *
 */
class DefaultPostRetrievalStrategy : PostRetrievalStrategy {

    override suspend fun retrievePosts(client: PostsWebServices, postDao: PostDao): List<PostUi> {
        client.getPosts()
            .map { postWs -> postWs.toDbModel() }
            .forEach { postDb -> postDao.insert(postDb) }

        return postDao
            .getAllPostsDb()
            .map { toUiModel(it) }
    }
}

/**
 * Local data strategy retrieves posts only from database
 *
 */
class LocalPostDataOnlyStrategy : PostRetrievalStrategy {

    override suspend fun retrievePosts(client: PostsWebServices, postDao: PostDao): List<PostUi> =
        postDao.getAllPostsDb().map { toUiModel(it) }

}

/**
 * Using this Strategy, posts are retrieved first from remote server only if there is none in database.
 * once retrieved, they are stored in database, and then served from database.
 */
class RemoteDataIfNecessaryStrategy: PostRetrievalStrategy {

    override suspend fun retrievePosts(client: PostsWebServices, postDao: PostDao): List<PostUi> {
        val numberOfPostsInDb = postDao.getPostsDbCount()
        if (numberOfPostsInDb == 0) {
            client.getPosts()
                .map { postWs -> postWs.toDbModel() }
                .forEach { postDb -> postDao.insert(postDb) }
        }
        return postDao
            .getAllPostsDb()
            .map { toUiModel(it) }
    }

}