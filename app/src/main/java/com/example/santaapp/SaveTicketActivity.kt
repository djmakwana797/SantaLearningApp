package com.example.santaapp

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.StyleSpan
import android.util.Log
import android.util.Patterns
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class SaveTicketActivity : AppCompatActivity() {
    private lateinit var saveTicketInfo: TextView
    private lateinit var fullNameInput: TextInputEditText
    private lateinit var phoneNumberInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var saveTicketBtn: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_save_ticket)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializeVal()
    }
    private fun initializeVal () {
        saveTicketInfo = findViewById(R.id.saveTicketInfo)
        fullNameInput = findViewById(R.id.fullNameInput)
        phoneNumberInput = findViewById(R.id.phoneNumberInput)
        emailInput = findViewById(R.id.emailInput)
        saveTicketBtn = findViewById(R.id.saveTicketBtn)

        val boldPart = "Please review your information."
        val normalPart = " Photo, phone number, and email are required."

        val spannable = SpannableString(boldPart + normalPart)
        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, boldPart.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        saveTicketInfo.text = spannable

        val inputFields = listOf(fullNameInput, phoneNumberInput, emailInput)
        inputFields.forEach { editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    validateInputs()
                }
            })
        }
        validateInputs()
    }

    private fun validateInputs() {
        Log.i("asdfasdf", "Validateddddd")
        val name = fullNameInput.text.toString().trim()
        val phone = phoneNumberInput.text.toString().trim()
        val email = emailInput.text.toString().trim()

        val isValidName = name.isNotEmpty()
        val isValidPhone = phone.length == 10 && phone.all { it.isDigit() }
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        val isFormValid = isValidName && isValidPhone && isValidEmail

        saveTicketBtn.isEnabled = isFormValid
    }

}