package com.hfrsoussama.projectplatine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfrsoussama.projectplatine.model.Post

class PostDetailsViewModel : ViewModel() {

    val selectedPost by lazy { MutableLiveData<Post>() }


    fun updatePost(post: Post) {
        selectedPost.value = post
    }

}