package com.keove.library.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridDecoration(columnCount: Int, spacing: Int) : ItemDecoration() {

    private var columnCount = 2
    private var spacing = 20

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % columnCount
        outRect.top = spacing
        outRect.bottom = spacing
        outRect.left = spacing / 2
        outRect.right = spacing / 2
        if (column == 0) {
            outRect.left = spacing
        }
        if (column == columnCount - 1) {
            outRect.right = spacing
        }
    }

    init {
        this.columnCount = columnCount
        this.spacing = spacing
    }
}