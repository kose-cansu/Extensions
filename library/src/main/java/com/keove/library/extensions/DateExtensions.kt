package com.keove.library.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.format(string: String): String = SimpleDateFormat(string,Locale("TR")).format(this)

