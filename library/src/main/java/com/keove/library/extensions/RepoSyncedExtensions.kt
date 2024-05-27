package com.keove.library.extensions

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avea.edergi.interfaces.RepoSynced

fun View.triggerRepoSynced() {
    if (this is RepoSynced) {
        (this as? RepoSynced)?.syncWithRepo()
    }
    if (this is ViewGroup) {
       if (this is RecyclerView) {
           (this as? RecyclerView).let {
               it?.getVisibleHolders()?.forEach {holder->
                   if (holder is RepoSynced) {
                       holder.syncWithRepo()
                   }
                   holder.itemView.triggerRepoSynced()
               }
           }
       }
       else {
            if (this.childCount > 0) {
                for (i in 0 until this.childCount) {
                    val view = this.getChildAt(i)
                    view.triggerRepoSynced()
                }
            }
       }
    }

}