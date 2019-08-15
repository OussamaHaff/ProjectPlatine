package com.hfrsoussama.projectplatine.feat.posts.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfrsoussama.projectplatine.feat.posts.core.PostsRepository
import com.hfrsoussama.projectplatine.feat.posts.core.model.Comment
import com.hfrsoussama.projectplatine.feat.posts.core.model.Post
import com.hfrsoussama.projectplatine.feat.posts.core.model.User
import com.hfrsoussama.projectplatine.feat.posts.ui.extensions.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel : ViewModel() {

    val postsList by lazy { MutableLiveData<List<Post>>() }

    val selectedPost by lazy { MutableLiveData<Post>() }

    val selectedPostUser by lazy { MutableLiveData<User>() }

    val selectedPostComments by lazy { MutableLiveData<List<Comment>>() }

    init {
        launch {
            postsList.value =
                try {
                    withContext(Dispatchers.IO) { PostsRepository().getPosts()}

                } catch (e: Throwable) {
                    Timber.e(e)
                    emptyList()
                }

        }
    }

    fun updateSelectedPost(post: Post) {
        selectedPost.value = post
        updateSelectedUser(post)
        updateSelectedPostComments(post)
    }

    private fun updateSelectedPostComments(post: Post) {
        launch {
            selectedPostComments.value = withContext(Dispatchers.IO) {
                PostsRepository().getCommentsByPostId(post.id)
            }
        }
    }

    private fun updateSelectedUser(post: Post) {
        launch {
            selectedPostUser.value = withContext(Dispatchers.IO) {
                PostsRepository().getUser(post.userId)
            }
        }
    }
}
