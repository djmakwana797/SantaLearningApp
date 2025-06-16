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
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var btnMenu: ImageView
    private lateinit var btnClose: ImageView
    private lateinit var itemPasses: LinearLayout
    private lateinit var myPasses: TextView
    private lateinit var myPassICon: ImageView

    private lateinit var itemAddAPass: LinearLayout
    private lateinit var addAPass: TextView
    private lateinit var addPassIcon: ImageView

    private lateinit var itemVisitRule: LinearLayout
    private lateinit var visitRule: TextView
    private lateinit var visitRuleICon: ImageView

    private lateinit var itemHelp: LinearLayout
    private lateinit var help: TextView
    private lateinit var helpIcon: ImageView

    private lateinit var itemSignOut: LinearLayout
    private lateinit var signOut: TextView
    private lateinit var signOutIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_pass)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeVal()
    }

    private fun initializeVal () {
        drawerLayout = findViewById(R.id.drawerLayout)
        btnMenu = findViewById(R.id.menu)
        btnClose = findViewById(R.id.btnCloseDrawer)

        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        btnClose.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        itemPasses = findViewById(R.id.nav_passes)
        myPasses = itemPasses.findViewById(R.id.title)
        myPasses.text = getString(R.string.my_passes)
        myPassICon = itemPasses.findViewById(R.id.icon)
        myPassICon.setImageResource(R.drawable.ic_placeholder)

        itemAddAPass = findViewById(R.id.nav_add_pass)
        addAPass = itemAddAPass.findViewById(R.id.title)
        addAPass.text = getString(R.string.add_a_pass)
        addPassIcon = itemAddAPass.findViewById(R.id.icon)
        addPassIcon.setImageResource(R.drawable.group_250)

        itemVisitRule = findViewById(R.id.nav_rules)
        visitRule = itemVisitRule.findViewById(R.id.title)
        visitRule.text = getString(R.string.visit_rules)
        visitRuleICon = itemVisitRule.findViewById(R.id.icon)
        visitRuleICon.setImageResource(R.drawable.group_254)

        itemHelp = findViewById(R.id.nav_help)
        help = itemHelp.findViewById(R.id.title)
        help.text = getString(R.string.help)
        helpIcon = itemAddAPass.findViewById(R.id.icon)
        helpIcon.setImageResource(R.drawable.group_252)

        itemSignOut = findViewById(R.id.nav_signout)
        signOut = itemSignOut.findViewById(R.id.title)
        signOut.text = getString(R.string.sign_out)
        signOutIcon = itemSignOut.findViewById(R.id.icon)
        signOutIcon.setImageResource(R.drawable.logout)
    }
}