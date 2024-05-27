package com.keove.library.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.WindowCompat
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


fun <T> Context.navigateToActivity(it: Class<T>, extras: Bundle? = null) {
    navigateToActivity(it,extras,Intent.FLAG_ACTIVITY_NEW_TASK)
}

fun <T> Context.navigateToActivity(klass: Class<T>, extras: Bundle? = null, flags:Int? = null) {
    val intent = Intent(this, klass)
    extras?.let {
        intent.putExtras(it)
    }
    flags?.let {
        intent.addFlags(it)
    }

    startActivity(intent)
}

fun Context.loadAssetString(name: String): String? {
    var `in`: BufferedReader? = null
    try {
        val buf = StringBuilder()
        val `is` = this.assets.open(name)
        `in` = BufferedReader(InputStreamReader(`is`))
        var str: String?
        var isFirst = true
        while (`in`.readLine().also { str = it } != null) {
            if (isFirst) isFirst = false else buf.append('\n')
            buf.append(str)
        }
        return buf.toString() }
    catch (e: IOException) {
        Log.e("ContextExtensions","ASSET FILE LOADER Error opening asset $name")
    }
    finally {
        if (`in` != null) {
            try {
                `in`.close()
            } catch (e: IOException) {
                Log.e("ContextExtensions","ASSET FILE LOADER Error opening asset $name")
            }
        }
    }
    return null
}

fun Activity.toggleLightStatusbar(light: Boolean) {
    WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = light
}