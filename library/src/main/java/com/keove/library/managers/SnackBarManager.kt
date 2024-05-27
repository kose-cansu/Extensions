package com.keove.library.managers

import android.app.Activity
import android.content.Context
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.keove.library.extensions.backgroundColor

object SnackBarManager {

    fun show(resId:Int,message:String,activity: Activity,context: Context) {
        val snackBar = Snackbar.make(activity.findViewById(android.R.id.content),message,2000).backgroundColor(resId,context)
        val snackBarView = snackBar.view
        val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.maxLines = 5
        snackBar.show()
    }


}