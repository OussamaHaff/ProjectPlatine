package com.hfrsoussama.projectplatine.feat.posts.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfrsoussama.projectplatine.feat.posts.core.PostsRepository
import com.hfrsoussama.projectplatine.feat.posts.core.extensions.launch
import com.hfrsoussama.projectplatine.feat.posts.core.model.Comment
import com.hfrsoussama.projectplatine.feat.posts.core.model.Post
import com.hfrsoussama.projectplatine.feat.posts.core.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val _postsList by lazy { MutableLiveData<List<Post>>() }
    val postsList: LiveData<List<Post>>
        get() = _postsList


    private val _selectedPost by lazy { MutableLiveData<Post>() }
    val selectedPost : LiveData<Post>
        get() = _selectedPost


    private val _selectedPostUser by lazy { MutableLiveData<User>() }
    val selectedPostUser :LiveData<User>
        get() = _selectedPostUser


    private val _selectedPostComments by lazy { MutableLiveData<List<Comment>>() }
    val selectedPostComments : LiveData<List<Comment>>
        get() = _selectedPostComments


    init {
        launch {
            _postsList.value =
                try {
                    withContext(Dispatchers.IO) { PostsRepository().getPosts() }

                } catch (e: Throwable) {
                    Timber.e(e)
                    emptyList()
                }

        }
    }

    fun updateSelectedPost(post: Post) {
        _selectedPost.value = post
        updateSelectedUser(post)
        updateSelectedPostComments(post)
    }

    private fun updateSelectedPostComments(post: Post) {
        launch {
            _selectedPostComments.value = withContext(Dispatchers.IO) {
                PostsRepository().getCommentsByPostId(post.id)
            }
        }
    }

    private fun updateSelectedUser(post: Post) {
        launch {
            _selectedPostUser.value = withContext(Dispatchers.IO) {
                PostsRepository().getUser(post.userId)
            }
        }
    }

    fun loadFirstPost() {
        _selectedPost.value = this.postsList.value?.first()
    }
}
