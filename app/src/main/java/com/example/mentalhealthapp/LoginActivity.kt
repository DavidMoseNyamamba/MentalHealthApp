package com.example.mentalhealthapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalhealthapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseManager.auth

        binding.buttonPerformLogin.setOnClickListener {
            handleLogin()
        }

        binding.textViewRegisterLink.setOnClickListener {
            // Navigate to RegisterActivity
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun handleLogin() {
        val email = binding.editTextLoginEmail.text.toString().trim()
        val password = binding.editTextLoginPassword.text.toString()

        if (!validateInput(email, password)) {
            return // Validation failed
        }

        // Show loading state
        binding.buttonPerformLogin.isEnabled = false
        binding.buttonPerformLogin.text = "Logging in..."

        // Firebase Authentication
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                binding.buttonPerformLogin.isEnabled = true
                binding.buttonPerformLogin.text = "Login"

                if (task.isSuccessful) {
                    // Login success
                    val user = auth.currentUser
                    Toast.makeText(this, "Welcome back, ${user?.email}!", Toast.LENGTH_SHORT).show()

                    // Log analytics event
                    FirebaseManager.analytics.logEvent("user_login", null)

                    // Navigate to MainActivity
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity() // Clear back stack
                } else {
                    // Login failed
                    val errorMessage = task.exception?.message ?: "Login failed"
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.editTextLoginEmail.error = "Email is required"
            binding.editTextLoginEmail.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextLoginEmail.error = "Enter a valid email address"
            binding.editTextLoginEmail.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            binding.editTextLoginPassword.error = "Password is required"
            binding.editTextLoginPassword.requestFocus()
            return false
        }

        // You might have other password validation rules here depending on your requirements
        // Clear previous errors if any
        binding.editTextLoginEmail.error = null
        binding.editTextLoginPassword.error = null
        return true
    }
}
