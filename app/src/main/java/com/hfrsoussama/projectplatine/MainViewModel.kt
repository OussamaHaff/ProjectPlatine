package com.hfrsoussama.projectplatine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfrsoussama.projectplatine.model.Post
import com.hfrsoussama.projectplatine.model.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    val postsList by lazy { MutableLiveData<List<Post>>() }

    val selectedPost by lazy { MutableLiveData<Post>() }

    init {
        viewModelScope.launch {
            val posts = PostsRepository().getPosts()
            posts.let {
                postsList.value = it
                selectedPost.value = it.first()
            }
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

    private companion object {
        private const val WAITING_TIME_IN_MILLI_SECONDS = 3000L
    }

}
