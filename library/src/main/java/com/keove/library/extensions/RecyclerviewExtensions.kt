package com.keove.library.extensions

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


fun RecyclerView.getVisibleHolders() : ArrayList<ViewHolder> {
    val firstVisible: Int
    val lastVisible: Int
    var isGrid = false
    if (this.layoutManager is GridLayoutManager) {
        isGrid = true
    }
    if(isGrid) {
        firstVisible = (this.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
        lastVisible = (this.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
    }
    else {
        if (this.layoutManager is LinearLayoutManager) {
            firstVisible = (this.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            lastVisible = (this.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }
        else {
            firstVisible = 0
            lastVisible = 0
        }
    }

    val list : ArrayList<ViewHolder> = ArrayList()
    for (i in firstVisible..lastVisible) {
        this.findViewHolderForAdapterPosition(i)?.let {
            list.add(it)
        }
    }
    return list

}