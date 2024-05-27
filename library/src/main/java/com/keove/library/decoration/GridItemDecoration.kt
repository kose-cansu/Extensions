package com.keove.library.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridItemDecoration(columnCount: Int, top: Int,bottom: Int,left: Int,right: Int,) : ItemDecoration() {

    private var columnCount = 2
    private var top = 10
    private var bottom = 10
    private var left = 20
    private var right = 20

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % columnCount
        outRect.top = top
        outRect.bottom = bottom
        outRect.left = left
        outRect.right = right
        if (column == 0) {
            outRect.left = left
        }
        if (column == columnCount - 1) {
            outRect.right = right
        }
    }

    init {
        this.columnCount = columnCount
        this.top = top
        this.bottom = bottom
        this.left = left
        this.right = right
    }
}