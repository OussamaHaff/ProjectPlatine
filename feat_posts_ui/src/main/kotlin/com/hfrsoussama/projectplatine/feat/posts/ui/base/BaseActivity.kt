package com.hfrsoussama.projectplatine.feat.posts.ui.base

import android.content.Context
import android.graphics.Shader
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.hfrsoussama.projectplatine.feat.posts.ui.R
import com.hfrsoussama.projectplatine.feat.posts.ui.decoration.TileVectorDrawable

abstract class BaseActivity : AppCompatActivity() {

    override fun onContentChanged() {
        super.onContentChanged()

        val backgroundView : View = window.decorView.findViewById(android.R.id.content)
        addBackgroundMosaique(this, backgroundView)
    }

    private fun addBackgroundMosaique(context: Context, backgroundView: View) {
        val drawable = AppCompatResources.getDrawable(context, R.drawable.ic_leaf_pattern)
        drawable?.let {
            backgroundView.background =
                TileVectorDrawable(
                    it,
                    Shader.TileMode.REPEAT
                )
        }
    }

}
