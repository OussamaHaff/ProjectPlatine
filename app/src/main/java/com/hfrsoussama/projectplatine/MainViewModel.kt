package com.hfrsoussama.projectplatine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfrsoussama.projectplatine.model.Comment
import com.hfrsoussama.projectplatine.model.Post
import com.hfrsoussama.projectplatine.model.PostsRepository
import com.hfrsoussama.projectplatine.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    val postsList by lazy { MutableLiveData<List<Post>>() }

    val selectedPost by lazy { MutableLiveData<Post>() }

    val selectedPostUser by lazy { MutableLiveData<User>() }

    val selectedPostComments by lazy { MutableLiveData<List<Comment>>() }

    init {
        viewModelScope.launch {
            postsList.value = PostsRepository().getPosts()
        }
    }


    private suspend fun fetchPostsFromServer(): List<Post> = withContext(Dispatchers.IO) {
        println(getThreadName("fetchPostsFromServer"))
        delay(timeMillis = WAITING_TIME_IN_MILLI_SECONDS)
        PostsProvider.getListOfPosts().reversed()
    }


    private fun getThreadName(methodName: String) {
        println("$methodName running on thread : ${Thread.currentThread().name}")
    }

    fun updateSelectedPost(post: Post) {
        selectedPost.value = post
        updateSelectedUser(post)
        updateSelectedPostComments(post)
    }

    private fun updateSelectedPostComments(post: Post) {
        viewModelScope.launch {
            selectedPostComments.value = withContext(Dispatchers.IO) {
                delay(30_000)
                PostsRepository().getCommentsByPostId(post.id)
            }

            println(selectedPostComments.value)
        }
    }

    private fun updateSelectedUser(post: Post) {
        viewModelScope.launch {
            selectedPostUser.value = withContext(Dispatchers.IO) {
                delay(30_000)
                PostsRepository().getUser(post.userId)
            }
            println(selectedPostUser.value)
        }
    }

    private companion object {
        private const val WAITING_TIME_IN_MILLI_SECONDS = 3000L
    }

}
