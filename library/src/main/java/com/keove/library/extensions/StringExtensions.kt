package com.keove.library.extensions

val String.safeFloat : Float?
    get() {
        return try {
            this.toFloat()
        } catch (e: Exception) {
            null
        }
    }