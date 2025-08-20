package com.example.mentalhealthapp // Replace with your package

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.focus.requestFocus
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.text
import com.example.mentalhealthapp.databinding.ActivityLoginBinding // Assuming your layout is activity_login.xml

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPerformLogin.setOnClickListener {
            handleLogin()
        }

        binding.textViewRegisterLink.setOnClickListener {
            // Navigate to RegisterActivity
            startActivity(Intent(this, RegisterActivity::class.java))
            // Optional: finish LoginActivity if you don't want it in back stack when user goes to register
            // finish()
        }
    }

    private fun handleLogin() {
        val email = binding.editTextLoginEmail.text.toString().trim()
        val password = binding.editTextLoginPassword.text.toString() // No trim for password

        if (!validateInput(email, password)) {
            return // Validation failed
        }

        // --- TODO: Perform Actual Login ---
        // This is where you would integrate with your backend or Firebase for authentication.
        // For example, using Firebase Authentication:
        //
        // FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        //    .addOnCompleteListener { task ->
        //        if (task.isSuccessful) {
        //            // Login success
        //            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
        //            // Navigate to MainActivity
        //            startActivity(Intent(this, MainActivity::class.java))
        //            finishAffinity() // Clear back stack so user can't go back to login screen
        //        } else {
        //            // Login failed
        //            Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
        //        }
        //    }

        // Placeholder for now:
        Toast.makeText(this, "Login logic for '$email' would go here.", Toast.LENGTH_LONG).show()
        // Simulate success and navigate to main app
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
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

