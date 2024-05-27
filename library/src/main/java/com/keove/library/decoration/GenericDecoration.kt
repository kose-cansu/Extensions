package com.keove.library.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GenericDecoration(left: Int, right: Int, top: Int, bottom: Int) : ItemDecoration() {

    private var left = 0
    private var right = 0
    private var top = 0
    private var bottom = 0

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect[left, top, right] = bottom
    }

    init {
        this.left = left
        this.right = right
        this.top = top
        this.bottom = bottom
    }
}
