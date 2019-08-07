package com.hfrsoussama.projectplatine.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import androidx.lifecycle.ViewModelProviders
import com.hfrsoussama.projectplatine.MainViewModel
import com.hfrsoussama.projectplatine.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModelProviders.of(this)[MainViewModel::class.java]

        supportFragmentManager.commitNow {
            replace(posts_list_fragment_container.id,
                PostsFragment.newInstance()
            )
        }

        addPostDetailsFragmentIfNeeded()


    }

    private fun addPostDetailsFragmentIfNeeded() {
        if (post_details_fragment_container != null) {
            supportFragmentManager.commitNow {
                replace(post_details_fragment_container.id,
                    PostDetailsFragment.newInstance()
                )
            }
        }
    }
}
