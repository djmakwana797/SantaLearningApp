package com.example.santaapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class AuthenticateCodeActivity : AppCompatActivity() {
    private lateinit var otpFields: List<TextInputEditText>
    private lateinit var authInfo: TextView
    private lateinit var btnContinue: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_authenticate_code)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setAuthString()
        setupOtpInputs()
    }
    private fun setAuthString() {
        val phoneNumber = "+16462331953"
        val fullText = "Enter the 7-digit code we just texted to your number $phoneNumber."
        val start = fullText.indexOf(phoneNumber)
        val end = start + phoneNumber.length

        val spannable = SpannableString(fullText)
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        authInfo = findViewById(R.id.verificationText)
        authInfo.text = spannable
    }
    private fun setupOtpInputs() {
        otpFields = listOf(
            findViewById(R.id.otpInput1),
            findViewById(R.id.otpInput2),
            findViewById(R.id.otpInput3),
            findViewById(R.id.otpInput4),
            findViewById(R.id.otpInput5),
            findViewById(R.id.otpInput6),
            findViewById(R.id.otpInput7)
        )

        for (i in otpFields.indices) {
            otpFields[i].addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1 && i < otpFields.size - 1) {
                        otpFields[i + 1].requestFocus()
                    } else if (s?.isEmpty() == true && i > 0) {
                        otpFields[i - 1].requestFocus()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

        btnContinue = findViewById(R.id.btnContinue)
        btnContinue.setOnClickListener {
            val intent = Intent(this, AuthSuccessActivity::class.java)
            startActivity(intent)
        }
    }
}