package com.hfrsoussama.projectplatine.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.hfrsoussama.projectplatine.CommentsProvider
import com.hfrsoussama.projectplatine.MainViewModel
import com.hfrsoussama.projectplatine.R
import com.hfrsoussama.projectplatine.UsersProvider
import com.hfrsoussama.projectplatine.model.Post
import kotlinx.android.synthetic.main.fragment_post_details.*

class PostDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PostDetailsFragment()
    }

    private val sharedViewModel: MainViewModel by activityViewModels()

    private val observer by lazy {
        Observer<Post> { post -> renderUiForPost(post) }
    }

    private fun renderUiForPost(post: Post) {
        tv_post_title.text = post.title
        tv_post_body.text = post.body
        tv_post_author_name.text = UsersProvider.getAuthorOfPost(post).name
        tv_post_number_of_comments.text = CommentsProvider.getNumberOfCommentsForPost(
            post
        ).toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.selectedPost.observe(this, observer)
    }

}
