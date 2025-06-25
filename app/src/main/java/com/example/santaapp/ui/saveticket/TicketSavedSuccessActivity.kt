package com.example.santaapp.ui.saveticket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.santaapp.R
import com.example.santaapp.databinding.ActivityTicketSavedSuccessBinding
import com.example.santaapp.ui.addpass.AddPassActivity
import com.example.santaapp.util.NavigationUtils

class TicketSavedSuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTicketSavedSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ticket_saved_success)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setBtnListeners()
    }

    private fun setBtnListeners () {
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, AddPassActivity::class.java)
            startActivity(intent)
        }
        binding.ivBack.setOnClickListener {
            NavigationUtils.goBack(this)
        }
    }
}