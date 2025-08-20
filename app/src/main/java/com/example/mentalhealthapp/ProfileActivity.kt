package com.example.mentalhealthapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var buttonLogout: Button
    private lateinit var buttonBackToMain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize Firebase Auth
        auth = FirebaseManager.auth

        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        buttonLogout = findViewById(R.id.buttonLogout)
        buttonBackToMain = findViewById(R.id.buttonBackToMain)
    }

    private fun setupClickListeners() {
        buttonLogout.setOnClickListener {
            performLogout()
        }

        buttonBackToMain.setOnClickListener {
            finish() // Go back to MainActivity
        }
    }

    private fun performLogout() {
        // Sign out from Firebase
        auth.signOut()

        // Show confirmation message
        Toast.makeText(this, "You have been logged out successfully", Toast.LENGTH_SHORT).show()

        // Navigate to WelcomeActivity and clear the back stack
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
