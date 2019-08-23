package com.hfrsoussama.projectplatine.feat.posts.ui.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CommentsRecyclerViewItemDecoration(
    private val spaceBetweenItems: Int
): RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            bottom = spaceBetweenItems
            left = spaceBetweenItems
            right = spaceBetweenItems
        }
    }
}