package com.example.santaapp.ui.addticket

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.santaapp.R
import com.example.santaapp.ui.scanticket.ScanTicketActivity
import com.example.santaapp.databinding.ActivityAddTicketBinding
import com.example.santaapp.util.NavigationUtils

class AddTicketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTicketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_ticket)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setBtnListeners()
    }

    private fun setBtnListeners() {
        binding.btnAddMyTicket.setOnClickListener {
            val intent = Intent(this, ScanTicketActivity::class.java)
            startActivity(intent)
        }
        binding.ivBack.setOnClickListener {
            NavigationUtils.goBack(this)
        }
    }
}