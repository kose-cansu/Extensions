package com.keove.library.extensions

import android.content.Context
import com.google.android.material.snackbar.Snackbar


fun Snackbar.backgroundColor(resId:Int,context:Context) : Snackbar {
    view.setBackgroundColor(context.resources.getColor(resId))
    return this
}

