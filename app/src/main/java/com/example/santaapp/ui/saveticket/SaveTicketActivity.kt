package com.example.santaapp.ui.saveticket

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
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.santaapp.R
import com.example.santaapp.databinding.ActivitySaveTicketBinding

class SaveTicketActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaveTicketBinding
    private lateinit var captureImageLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_save_ticket)
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
                    binding.userImage.visibility = View.VISIBLE
                    binding.imageInput.visibility = View.INVISIBLE
                    binding.userImage.setImageURI(it)
                }
            }
        }

        val boldPart = "Please review your information."
        val normalPart = " Photo, phone number, and email are required."

        val spannable = SpannableString(boldPart + normalPart)
        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, boldPart.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.saveTicketInfo.text = spannable

        binding.textInputLayout.setEndIconOnClickListener {
            binding.fullNameInput.isEnabled = true
            binding.fullNameInput.requestFocus()
            binding.fullNameInput.setSelection(binding.fullNameInput.text?.length ?: 0)
        }
        binding.textInputLayout1.setEndIconOnClickListener {
            binding.phoneNumberInput.isEnabled = true
            binding.phoneNumberInput.requestFocus()
            binding.phoneNumberInput.setSelection(binding.phoneNumberInput.text?.length ?: 0)
        }

        binding.textInputLayout2.setEndIconOnClickListener {
            binding.emailInput.isEnabled = true
            binding.emailInput.requestFocus()
            binding.emailInput.setSelection(binding.emailInput.text?.length ?: 0)
        }

        binding.saveTicketBtn.setOnClickListener {
            val intent = Intent(this, TicketSavedSuccessActivity::class.java)
            startActivity(intent)
        }

        binding.imageInput.setOnClickListener {
            val intent = Intent(this, VerifyPhotoActivity::class.java)
            captureImageLauncher.launch(intent)

        }
        val inputFields = listOf(binding.fullNameInput, binding.phoneNumberInput, binding.emailInput)
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
        val name = binding.fullNameInput.text.toString().trim()
        val phone = binding.phoneNumberInput.text.toString().trim()
        val email = binding.emailInput.text.toString().trim()

        val isValidName = name.isNotEmpty()
        val isValidPhone = phone.length == 10 && phone.all { it.isDigit() }

        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        binding.errorImgName.setImageResource(if (isValidName) R.drawable.tick else R.drawable.error)
        binding.errorImgPhone.setImageResource(if (isValidPhone) R.drawable.tick else R.drawable.error)
        binding.errorImgEmail.setImageResource(if (isValidEmail) R.drawable.tick else R.drawable.error)

        binding.saveTicketBtn.isEnabled = isValidName && isValidPhone && isValidEmail
        if(isValidName && isValidPhone && isValidEmail) {
            binding.saveTicketBtn.text = "Save ticket"
        }
    }
}