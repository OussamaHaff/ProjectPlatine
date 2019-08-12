package com.hfrsoussama.projectplatine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfrsoussama.projectplatine.model.Comment
import com.hfrsoussama.projectplatine.model.Post
import com.hfrsoussama.projectplatine.model.PostsRepository
import com.hfrsoussama.projectplatine.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    val postsList by lazy { MutableLiveData<List<Post>>() }

    val selectedPost by lazy { MutableLiveData<Post>() }

    val selectedPostUser by lazy { MutableLiveData<User>() }

    val selectedPostComments by lazy { MutableLiveData<List<Comment>>() }

    init {
        launch {
            postsList.value = PostsRepository().getPosts()
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
