package com.hfrsoussama.projectplatine.feat.posts.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfrsoussama.projectplatine.feat.posts.core.PostsRepository
import com.hfrsoussama.projectplatine.feat.posts.core.extensions.launch
import com.hfrsoussama.projectplatine.feat.posts.core.model.extensions.toUiModel
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.UserUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.CommentWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.PostWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.UserWs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val _postsList by lazy { MutableLiveData<List<PostUi>>() }
    val postsList: LiveData<List<PostUi>>
        get() = _postsList


    private val _selectedPost by lazy { MutableLiveData<PostUi>() }
    val selectedPost : LiveData<PostUi>
        get() = _selectedPost


    private val _selectedPostUser by lazy { MutableLiveData<UserUi>() }
    val selectedPostUser :LiveData<UserUi>
        get() = _selectedPostUser


    private val _selectedPostComments by lazy { MutableLiveData<List<CommentUi>>() }
    val selectedPostComments : LiveData<List<CommentUi>>
        get() = _selectedPostComments


    init {
        launch {
            _postsList.value =
                try {
                    withContext(Dispatchers.IO) {
                        PostsRepository().getPosts()
                            .asSequence()
                            .map(PostWs::toUiModel)
                            .toList()
                    }

                } catch (e: Throwable) {
                    Timber.e(e)
                    emptyList()
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
            _selectedPostComments.value = withContext(Dispatchers.IO) {
                PostsRepository().getCommentsByPostId(post.id)
                    .asSequence()
                    .map(CommentWs::toUiModel)
                    .toList()
            }
        }
    }

    private fun updateSelectedUser(post: PostUi) {
        launch {
            _selectedPostUser.value = withContext(Dispatchers.IO) {
                PostsRepository().getUser(post.userId).toUiModel()
            }
        }
    }

    fun loadFirstPost() {
        _selectedPost.value = this.postsList.value?.first()
    }
}