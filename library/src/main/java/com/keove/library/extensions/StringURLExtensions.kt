package com.keove.library.extensions

fun String.applyQueryParameters(params:HashMap<String,String>) : String {

    var parameterized = "${this}?"

    params.forEach { (key, value) ->
        parameterized = "${parameterized}${key}=${value}&"
    }

    return parameterized
}

fun String.applyWidth(width:Int) : String {
    return this.applyQueryParameters(hashMapOf("width" to width.toString()))
}