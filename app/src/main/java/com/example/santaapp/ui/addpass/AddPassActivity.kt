package com.example.santaapp.ui.addpass

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.santaapp.R
import com.example.santaapp.databinding.ActivityAddPassBinding

class AddPassActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_pass)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeView()
    }

    private fun initializeView () {
        with(binding){
            ivMenu.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }

            btnCloseDrawer.setOnClickListener {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        setupNavItem(binding.navPasses, R.string.my_passes, R.drawable.ic_wallet)
        setupNavItem(binding.navAddPass, R.string.add_a_pass, R.drawable.ic_add_pass)
        setupNavItem(binding.navRules, R.string.visit_rules, R.drawable.ic_rules)
        setupNavItem(binding.navHelp, R.string.help, R.drawable.ic_help)
        setupNavItem(binding.navSignout, R.string.sign_out, R.drawable.ic_logout)
    }

    private fun setupNavItem(item: View, titleRes: Int, iconRes: Int) {
        item.findViewById<TextView>(R.id.title).text = getString(titleRes)
        item.findViewById<ImageView>(R.id.icon).setImageResource(iconRes)
    }
}