package com.example.santaapp.ui.auth

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel :  ViewModel() {
    private val number = "+16462331953"
    private val fullText = "Enter the 7-digit code we just texted to your number $number."
    val phoneNumber = MutableLiveData<CharSequence>(fullText)

    val otpDigits = MutableList(7) { MutableLiveData<String>() }

    val otpCode: LiveData<String> = MediatorLiveData<String>().apply {
        otpDigits.forEach { digitLiveData ->
            addSource(digitLiveData) {
                value = otpDigits.joinToString("") { it.value.orEmpty() }
            }
        }
    }

    init {
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
