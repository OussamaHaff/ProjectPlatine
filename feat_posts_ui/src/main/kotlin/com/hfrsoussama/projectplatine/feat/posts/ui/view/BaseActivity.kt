package com.hfrsoussama.projectplatine.feat.posts.ui.view

import android.content.Context
import android.graphics.Shader
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hfrsoussama.projectplatine.feat.posts.ui.R

abstract class BaseActivity : AppCompatActivity() {

    abstract fun isTablet(): Boolean

    override fun onContentChanged() {
        super.onContentChanged()

        val backgroundView : View = window.decorView.findViewById(android.R.id.content)
        addBackgroundMosaique(this, backgroundView)
    }

    private fun addBackgroundMosaique(context: Context, backgroundView: View) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_leaf_pattern)
        drawable?.let {
            backgroundView.background = TileVectorDrawable(it, Shader.TileMode.REPEAT)
        }
    }

}
