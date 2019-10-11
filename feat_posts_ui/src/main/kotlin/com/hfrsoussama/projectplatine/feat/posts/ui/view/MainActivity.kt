package com.hfrsoussama.projectplatine.feat.posts.ui.view

import android.os.Bundle
import androidx.fragment.app.commitNow
import androidx.lifecycle.Observer
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.feat.posts.core.viewmodel.MainViewModel
import com.hfrsoussama.projectplatine.feat.posts.ui.R
import com.hfrsoussama.projectplatine.feat.posts.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val selectedPostObserver = Observer<PostUi> { attachDetailsFragmentIfNeeded() }


    private fun attachDetailsFragmentIfNeeded() {
        supportFragmentManager.beginTransaction()
            .replace(
                main_fragment_container.id,
                PostDetailsFragment.newInstance(),
                PostDetailsFragment.TAG
            )
            .addToBackStack(PostDetailsFragment.TAG)
            .commit()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.selectedPost.observe(this, selectedPostObserver)

        supportFragmentManager.commitNow {
            replace(
                main_fragment_container.id,
                PostsFragment.newInstance(),
                PostsFragment.TAG
            )
        }
    }

}
