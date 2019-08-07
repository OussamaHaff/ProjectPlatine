package com.hfrsoussama.projectplatine.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hfrsoussama.projectplatine.*
import com.hfrsoussama.projectplatine.model.Post
import com.hfrsoussama.projectplatine.model.User
import kotlinx.android.synthetic.main.fragment_post_details.*

class PostDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PostDetailsFragment()
        const val TAG = "PostDetailsFragment"
    }

    private val sharedViewModel: MainViewModel by activityViewModels()

    private val fragmentViewModel by lazy {
        ViewModelProviders.of(this)[PostDetailsViewModel::class.java]
    }

    private val selectedPostObserver by lazy { Observer<Post> { onPostSelected(it) } }

    private val selectedPostUserObserver by lazy { Observer<User> { onUserReceived(it) } }

    private fun onUserReceived(user: User) {
        tv_post_author_name.text = user.name
    }

    private fun onPostSelected(post: Post) {
        fragmentViewModel.selectedPost.value = post
        renderUiForPost(post)
    }

    private fun renderUiForPost(post: Post) {
        tv_post_title.text = post.title
        tv_post_body.text = post.body
        tv_post_number_of_comments.text = CommentsProvider.getNumberOfCommentsForPost(
            post
        ).toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.selectedPost.observe(this, selectedPostObserver)
        sharedViewModel.selectedPostUser.observe(this, selectedPostUserObserver)
    }

}
