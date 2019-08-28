package com.hfrsoussama.projectplatine.feat.posts.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.feat.posts.core.viewmodel.MainViewModel
import com.hfrsoussama.projectplatine.feat.posts.ui.R
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.PostWs
import kotlinx.android.synthetic.main.fragment_posts.*

class PostsFragment : Fragment(), PostsListAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = PostsFragment()

        const val TAG = "PostsFragment"
    }

    private val sharedViewModel: MainViewModel by activityViewModels()

    private val postListObserver by lazy {
        Observer<List<PostUi>> { posts -> renderListOfPosts(posts) }
    }

    private fun renderListOfPosts(posts: List<PostUi>) {
        rv_posts_list?.apply {
            adapter = PostsListAdapter(posts, this@PostsFragment)

            layoutManager = LinearLayoutManager(context).apply {
                orientation =  RecyclerView.HORIZONTAL
            }

            layoutAnimation = AnimationUtils.loadLayoutAnimation(
                this@PostsFragment.context,
                R.anim.layout_animation_easy_drop
            )

            addItemDecoration(
                PostsRecyclerViewItemDecoration(
                    resources.getDimension(R.dimen.recycler_view_medium_item_spacing).toInt()
                )
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.postsList.observe(this, postListObserver)
    }

    override fun onItemClick(post: PostUi) {
        sharedViewModel.updateSelectedPost(post)
    }

}
