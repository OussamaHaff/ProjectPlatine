package com.hfrsoussama.projectplatine.feat.posts.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commitNow
import androidx.lifecycle.Observer
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.feat.posts.ui.R
import com.hfrsoussama.projectplatine.feat.posts.core.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun isTablet() = post_details_fragment_container != null

    private val viewModel: MainViewModel by viewModels()

    private val postsListObserver by lazy {
        Observer<List<PostUi>> { updateSelectedPostForTablet() }
    }

    private val selectedPostObserver by lazy {
        Observer<PostUi> { attachDetailsFragmentIfNeeded() }
    }

    /**
     * Because the activity replaces automatically the [PostsFragment] by [PostDetailsFragment] when a post is selected,
     * we use this method to avoid the side effect of having it replaced right after loading the list of posts.
     *
     * After loading the list of posts, the value of [MainViewModel.selectedPost] is set only if the app is used on a
     * tablet in order to update the post details fragment.
     *
     */
    private fun updateSelectedPostForTablet() {
        if (isTablet()) {
            viewModel.loadFirstPost()
        }
    }


    private fun attachDetailsFragmentIfNeeded() {
        if (isTablet()) {
            // Do nothing, the com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
            // Details Fragment should be already attached
        } else {
            // Attach the fragment
            supportFragmentManager.beginTransaction()
                .replace(
                    main_fragment_container.id,
                    PostDetailsFragment.newInstance(),
                    PostDetailsFragment.TAG
                )
                .addToBackStack(PostDetailsFragment.TAG)
                .commit()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.postsList.observe(this, postsListObserver)
        viewModel.selectedPost.observe(this, selectedPostObserver)

        supportFragmentManager.commitNow {
            replace(
                main_fragment_container.id,
                PostsFragment.newInstance(),
                PostsFragment.TAG
            )
        }

        addPostDetailsFragmentIfNeeded(isTablet())
    }

    private fun addPostDetailsFragmentIfNeeded(isTablet: Boolean) {
        if (isTablet) {
            supportFragmentManager.commitNow {
                replace(post_details_fragment_container.id,
                    PostDetailsFragment.newInstance()
                )
            }
        }
    }
}
