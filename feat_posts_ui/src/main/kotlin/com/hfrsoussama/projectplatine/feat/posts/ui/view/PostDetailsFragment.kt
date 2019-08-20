package com.hfrsoussama.projectplatine.feat.posts.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.hfrsoussama.projectplatine.feat.posts.ui.R
import com.hfrsoussama.projectplatine.feat.posts.ui.viewmodel.MainViewModel
import com.hfrsoussama.projectplatine.feat.posts.core.model.extensions.generateAvatarHttpUrl
import com.hfrsoussama.projectplatine.feat.posts.core.model.Comment
import com.hfrsoussama.projectplatine.feat.posts.core.model.Post
import com.hfrsoussama.projectplatine.feat.posts.core.model.User
import kotlinx.android.synthetic.main.fragment_post_details.*

class PostDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PostDetailsFragment()
        const val TAG = "PostDetailsFragment"
    }

    private val sharedViewModel: MainViewModel by activityViewModels()

    private val selectedPostObserver by lazy { Observer<Post> { onPostSelected(it) } }

    private val selectedPostUserObserver by lazy { Observer<User> { onUserReceived(it) } }

    private val selectedPostCommentsObserver by lazy { Observer<List<Comment>> { onCommentsReceived(it) } }

    private fun onCommentsReceived(commentsList: List<Comment>) {
        rv_comments_list?.apply {
            adapter = CommentsListAdapter(commentsList)
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }
    }

    private fun onUserReceived(user: User) {
        tv_post_author_name.text = user.name
        iv_author_avatar.load(user.generateAvatarHttpUrl()) {
            crossfade(true)
            placeholder(android.R.drawable.ic_input_add)
            transformations(CircleCropTransformation())
        }
    }

    private fun onPostSelected(post: Post) {
        renderUiForPost(post)
    }

    private fun renderUiForPost(post: Post) {
        tv_post_title.text = post.title
        tv_post_body.text = post.body
        tv_post_author_name.text = post.userId.toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.apply {
            selectedPost.observe(this@PostDetailsFragment, selectedPostObserver)
            selectedPostUser.observe(this@PostDetailsFragment, selectedPostUserObserver)
            selectedPostComments.observe(this@PostDetailsFragment, selectedPostCommentsObserver)
        }
    }

}
