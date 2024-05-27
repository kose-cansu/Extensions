package com.keove.library.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object UXUtils {

    fun hideKeyboard(context: Context, view: View) {
        val manager =
            context.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    fun disableViews(vararg views: View) {
        for (btn in views) {
            btn.alpha = 0.8f
            btn.isEnabled = false
        }
    }

    fun enableViews(vararg views: View) {
        for (btn in views) {
            btn.alpha = 1.0f
            btn.isEnabled = true
        }
    }
}