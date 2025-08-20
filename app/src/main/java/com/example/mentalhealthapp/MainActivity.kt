package com.example.mentalhealthapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var isLoggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isLoggedIn) {
            // Show login/register screen
            setContentView(R.layout.activity_main)
            setupLoginScreen()
        } else {
            // Show tabbed interface
            setContentView(R.layout.activity_main_tabs)
            setupTabbedInterface()
        }
    }

    private fun setupLoginScreen() {
        val loginButton = findViewById<android.widget.Button>(R.id.buttonLogin)
        val registerButton = findViewById<android.widget.Button>(R.id.buttonRegister)

        loginButton.setOnClickListener {
            // Simulate login success
            isLoggedIn = true
            // Switch to tabbed interface
            setContentView(R.layout.activity_main_tabs)
            setupTabbedInterface()
        }

        registerButton.setOnClickListener {
            // Handle registration - for now just show login screen
            // You can add registration logic here later
        }
    }

    private fun setupTabbedInterface() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set default fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, TrackerFragment())
            .commit()

        bottomNavigation.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_tracker -> TrackerFragment()
                R.id.nav_resources -> ResourcesFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> TrackerFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()

            true
        }
    }
}
