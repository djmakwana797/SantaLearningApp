package com.example.santaapp

import android.content.ContentValues
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.StyleSpan
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SaveTicketActivity : AppCompatActivity() {
    private lateinit var saveTicketInfo: TextView
    private lateinit var fullNameInput: TextInputEditText
    private lateinit var phoneNumberInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var saveTicketBtn: MaterialButton
    private lateinit var takeAPhoto: ConstraintLayout
    private lateinit var userImage: ImageView

    private lateinit var errorImgName: ImageView
    private lateinit var errorImgEmail: ImageView
    private lateinit var errorImgPhone: ImageView

    private lateinit var textInputLayout: TextInputLayout
    private lateinit var textInputLayout1: TextInputLayout
    private lateinit var textInputLayout2: TextInputLayout

    private lateinit var captureImageLauncher: ActivityResultLauncher<Intent>

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
        captureImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val uriString = result.data?.getStringExtra("capturedImageUri")
                val uri = uriString?.let { Uri.parse(it) }

                uri?.let {
                    userImage.visibility = View.VISIBLE
                    takeAPhoto.visibility = View.INVISIBLE
                    userImage.setImageURI(it)
                }
            }
        }
        saveTicketInfo = findViewById(R.id.saveTicketInfo)
        fullNameInput = findViewById(R.id.fullNameInput)
        phoneNumberInput = findViewById(R.id.phoneNumberInput)
        emailInput = findViewById(R.id.emailInput)
        saveTicketBtn = findViewById(R.id.saveTicketBtn)
        takeAPhoto =findViewById(R.id.imageInput)
        errorImgName = findViewById(R.id.errorImgName)
        errorImgEmail = findViewById(R.id.errorImgEmail)
        errorImgPhone = findViewById(R.id.errorImgPhone)
        textInputLayout = findViewById(R.id.textInputLayout)
        textInputLayout1 = findViewById(R.id.textInputLayout1)
        textInputLayout2 = findViewById(R.id.textInputLayout2)
        userImage = findViewById(R.id.userImage)

        val boldPart = "Please review your information."
        val normalPart = " Photo, phone number, and email are required."

        val spannable = SpannableString(boldPart + normalPart)
        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, boldPart.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        saveTicketInfo.text = spannable

        textInputLayout.setEndIconOnClickListener {
            fullNameInput.isEnabled = true
            fullNameInput.requestFocus()
            fullNameInput.setSelection(fullNameInput.text?.length ?: 0)
        }
        textInputLayout1.setEndIconOnClickListener {
            phoneNumberInput.isEnabled = true
            phoneNumberInput.requestFocus()
            phoneNumberInput.setSelection(phoneNumberInput.text?.length ?: 0)
        }

        textInputLayout2.setEndIconOnClickListener {
            emailInput.isEnabled = true
            emailInput.requestFocus()
            emailInput.setSelection(emailInput.text?.length ?: 0)
        }

        takeAPhoto.setOnClickListener {
            val intent = Intent(this, VerifyPhotoActivity::class.java)
            captureImageLauncher.launch(intent)

        }
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
        val name = fullNameInput.text.toString().trim()
        val phone = phoneNumberInput.text.toString().trim()
        val email = emailInput.text.toString().trim()

        val isValidName = name.isNotEmpty()
        val isValidPhone = phone.length == 10 && phone.all { it.isDigit() }

        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        errorImgName.setImageResource(if (isValidName) R.drawable.tick else R.drawable.error)
        errorImgPhone.setImageResource(if (isValidPhone) R.drawable.tick else R.drawable.error)
        errorImgEmail.setImageResource(if (isValidEmail) R.drawable.tick else R.drawable.error)

        saveTicketBtn.isEnabled = isValidName && isValidPhone && isValidEmail
        if(isValidName && isValidPhone && isValidEmail) {
            saveTicketBtn.text = "Save ticket"
        }
    }
}