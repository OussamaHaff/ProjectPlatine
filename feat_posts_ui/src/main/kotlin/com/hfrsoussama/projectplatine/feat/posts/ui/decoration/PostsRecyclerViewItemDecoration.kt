package com.hfrsoussama.projectplatine.feat.posts.ui.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PostsRecyclerViewItemDecoration(
    private val itemSpacing: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            left = itemSpacing
            top = itemSpacing
            bottom = itemSpacing
        }
    }
}
