package com.example.santaapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout

class AddPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_pass)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val btnMenu = findViewById<ImageView>(R.id.menu)
        val btnClose = findViewById<ImageView>(R.id.btnCloseDrawer)

        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        btnClose.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        val itemPasses = findViewById<LinearLayout>(R.id.nav_passes)
        itemPasses.findViewById<TextView>(R.id.title).text = "My Passes"
        itemPasses.findViewById<ImageView>(R.id.icon).setImageResource(R.drawable.ic_placeholder)

    }
}