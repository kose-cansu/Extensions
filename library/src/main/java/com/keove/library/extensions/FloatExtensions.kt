package com.keove.library.extensions

fun Float.roundTo(n : Int) : String {
    return "%.${n}f".format(this)
}