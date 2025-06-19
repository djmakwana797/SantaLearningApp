package com.example.santaapp.ui.authsuccess

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.santaapp.ui.addticket.AddTicketActivity
import com.example.santaapp.R
import com.example.santaapp.databinding.ActivityAuthSuccessBinding
import com.example.santaapp.util.NavigationUtils

class AuthSuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth_success)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializeVal()
    }
    private fun initializeVal () {
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, AddTicketActivity::class.java)
            startActivity(intent)
        }
        binding.backBtn.setOnClickListener {
            NavigationUtils.goBack(this)
        }
    }
}