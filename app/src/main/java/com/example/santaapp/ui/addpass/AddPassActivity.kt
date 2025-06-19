package com.example.santaapp.ui.addpass

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
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

        initializeVal()
    }

    private fun initializeVal () {
        binding.menu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.btnCloseDrawer.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }

        setupNavItem(binding.navPasses, R.string.my_passes, R.drawable.ic_placeholder)
        setupNavItem(binding.navAddPass, R.string.add_a_pass, R.drawable.group_250)
        setupNavItem(binding.navRules, R.string.visit_rules, R.drawable.group_254)
        setupNavItem(binding.navHelp, R.string.help, R.drawable.group_252)
        setupNavItem(binding.navSignout, R.string.sign_out, R.drawable.logout)
    }

    private fun setupNavItem(item: View, titleRes: Int, iconRes: Int) {
        item.findViewById<TextView>(R.id.title).text = getString(titleRes)
        item.findViewById<ImageView>(R.id.icon).setImageResource(iconRes)
    }

}