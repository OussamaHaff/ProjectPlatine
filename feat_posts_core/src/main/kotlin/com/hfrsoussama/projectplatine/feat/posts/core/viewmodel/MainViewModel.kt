package com.hfrsoussama.projectplatine.feat.posts.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfrsoussama.projectplatine.feat.posts.core.extensions.launch
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.UserUi
import com.hfrsoussama.projectplatine.feat.posts.core.network.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel(private val repository: PostsRepository) : ViewModel() {

    private val _postsList = MutableLiveData<List<PostUi>>()
    val postsList: LiveData<List<PostUi>>
        get() = _postsList


    private val _selectedPost = MutableLiveData<PostUi>()
    val selectedPost: LiveData<PostUi>
        get() = _selectedPost


    private val _selectedPostUser = MutableLiveData<UserUi>()
    val selectedPostUser: LiveData<UserUi>
        get() = _selectedPostUser


    private val _selectedPostComments = MutableLiveData<List<CommentUi>>()
    val selectedPostComments: LiveData<List<CommentUi>>
        get() = _selectedPostComments

    private val _errorState = MutableLiveData<Throwable>()
    val errorState : LiveData<Throwable>
        get() = _errorState


    init {
        launch {
            try {
                _postsList.value = withContext(Dispatchers.IO) {
                    delay(timeMillis = 2_000)
                    repository.getPosts()
                }

            } catch (e: Throwable) {
                Timber.e(e)
                _errorState.value = e
            }

        }
    }

    fun updateSelectedPost(post: PostUi) {
        _selectedPost.value = post
        updateSelectedUser(post)
        updateSelectedPostComments(post)
    }

    private fun updateSelectedPostComments(post: PostUi) {
        launch {
            try {
                _selectedPostComments.value = withContext(Dispatchers.IO) {
                    repository.getCommentsByPostId(post.id)
                }
            } catch (e: Throwable) {
                Timber.e(e)
            }
        }
    }

    private fun updateSelectedUser(post: PostUi) {
        launch {
            try {
                _selectedPostUser.value = withContext(Dispatchers.IO) {
                    repository.getUser(post.userId)
                }

            } catch (e: Throwable) {
                Timber.e(e)
            }
        }
    }
}
