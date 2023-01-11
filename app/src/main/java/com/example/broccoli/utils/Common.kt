package com.example.broccoli.utils

import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager

class LengthRule(private val length: Int) {

    fun validate(value: String): String? {
        return if (value.length < length) {
            "Must be at least $length characters long"
        } else {
            null
        }
    }
}

class EmailRule{
    private val email = Patterns.EMAIL_ADDRESS

    fun validate(value: String): String?{
        return if(!email.matcher(value).matches()){
            "Invalid email address"
        }else{
            null
        }
    }
}


