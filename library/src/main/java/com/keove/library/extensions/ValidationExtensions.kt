package com.keove.library.extensions

import android.widget.EditText
import java.util.regex.Pattern

fun String.validateMobile() = Pattern.compile("^905\\d{9}\$").matcher(this).matches()

fun String.validateSurname() = length > 2

fun String.validateName() = length > 2

fun String.validateMonth() = this.toInt() in 1..12

fun String.validateYear() = Pattern.compile("^[12][0-9]{3}\$").matcher(this).matches()

fun String.validatePassword() = Pattern.compile("^(?=.*[A-Z].*)(?=.*[0-9].*)(?=.*[a-z].*).{6,}$").matcher(this).matches()







fun EditText.validateName(emptyCheck:Boolean=false):Boolean {
    if (text.isNotEmpty()) {
        if(text.toString().validateName()) {
            error = null
            return true
        }
        error = null
        return false
    }
    else {
        if(emptyCheck) {
            error = null
            return false
        }
        error = null
        return true
    }
}

fun EditText.validateSurname(emptyCheck:Boolean=false):Boolean {
    if (text.isNotEmpty()) {
        if(text.toString().validateName()) {
            error = null
            return true
        }
        error = null
        return false
    }
    else {
        if(emptyCheck) {
            error = null
            return false
        }
        else error = null
        return true
    }
}

fun EditText.validateCity():Boolean {
    if (text.isNotEmpty()) {
        error = null
        return true
    }
    error = "Bir şehir seçin"
    return false
}

fun EditText.validateEmail():Boolean {
    val emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}\$"

    if (text.isNotEmpty()) {
        if (text.toString().matches(emailPattern.toRegex())) {
            error = null
            return  true
        }
        error = null
        return false
    } else {
        error = null
        return false
    }
}

fun EditText.validateYear(emptyCheck: Boolean=false):Boolean {
    if(text.isNotEmpty()) {
        if(text.toString().validateYear()) {
            error = null
            return true
        }
        error = "Geçersiz"
        return false
    }
    else {
        if(emptyCheck) {
            error = "Geçersiz"
            return false
        }
        error = null
        return true
    }
}

fun EditText.validatePassword(emptyCheck: Boolean=false):Boolean {
    if(text.isNotEmpty()) {
        if(text.toString().validatePassword()) {
            error = null
            return true
        }
        error = null
        return false
    }
    else {
        if(emptyCheck) {
            error = null
            return false
        }
        error = null
        return true
    }
}

fun EditText.validateEmailRe(email:String, emptyCheck: Boolean):Boolean {
    if(text.isNotEmpty()) {
        if (text.toString().contentEquals(email)) {
            error = null
            return true
        }
        error = "E-posta adresi eşleşmiyor"
        return false
    }
    else {
        if(emptyCheck) {
            error = "E-posta adresi eşleşmiyor"
            return false
        }
        error = null
        return true
    }
}

fun EditText.validatePasswordRe(password:String, emptyCheck: Boolean):Boolean {
    if (text.isNotEmpty()) {
        if(text.toString().contentEquals(password)) {
            error = null
            return true
        }
        error = null
        return false
    }
    else {
        if(emptyCheck) {
            error = null
            return false
        }
        error = null
        return true
    }
}











