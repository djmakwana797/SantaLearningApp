package com.example.santaapp.ui.auth

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel :  ViewModel() {
    val phoneNumber = MutableLiveData<CharSequence>()

    val otpDigits = MutableList(7) { MutableLiveData<String>() }

    fun setPhoneNumber(number: String) {
        val fullText = "Enter the 7-digit code we just texted to your number +91 $number."
        val spannable = SpannableString(fullText)
        val start = fullText.indexOf(number)
        val end = start + number.length

        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        phoneNumber.value = spannable
    }
}
