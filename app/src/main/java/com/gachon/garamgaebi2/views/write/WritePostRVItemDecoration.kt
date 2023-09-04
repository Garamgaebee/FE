package com.gachon.garamgaebi2.views.write

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class WritePostRVItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.bottom = space

    }
}