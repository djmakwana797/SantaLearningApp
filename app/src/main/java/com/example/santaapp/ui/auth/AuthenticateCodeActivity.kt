package com.example.santaapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.santaapp.R
import com.example.santaapp.databinding.ActivityAuthenticateCodeBinding
import com.example.santaapp.ui.authsuccess.AuthSuccessActivity
import com.example.santaapp.util.NavigationUtils

class AuthenticateCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticateCodeBinding
    private lateinit var viewModel : AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authenticate_code)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        binding.viewModel = viewModel
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupOtpInputs()
        setOnClickListeners()
    }
    private fun setupOtpInputs() {
        val otpInputs = listOf(
            binding.otpInput1,
            binding.otpInput2,
            binding.otpInput3,
            binding.otpInput4,
            binding.otpInput5,
            binding.otpInput6,
            binding.otpInput7
        )

        otpInputs.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1 && index < otpInputs.size - 1) {
                        otpInputs[index + 1].requestFocus()
                    } else if (s?.isEmpty() == true && index > 0) {
                        otpInputs[index - 1].requestFocus()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }

    private fun setOnClickListeners() {
        binding.backBtn.setOnClickListener {
            NavigationUtils.goBack(this)
        }
        binding.btnContinue.setOnClickListener{
            val intent = Intent(this, AuthSuccessActivity::class.java)
            startActivity(intent)
        }
    }
}