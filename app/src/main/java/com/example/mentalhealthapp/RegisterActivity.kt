package com.example.mentalhealthapp // Replace with your package

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.focus.requestFocus
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.text
import com.example.mentalhealthapp.databinding.ActivityRegisterBinding // Assuming your layout is activity_register.xml

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPerformRegister.setOnClickListener {
            handleRegistration()
        }

        binding.textViewLoginLink.setOnClickListener {
            // Navigate back to LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Optional: finish RegisterActivity so user can't go back to it with back button
        }
    }

    private fun handleRegistration() {
        val fullName = binding.editTextRegisterFullName.text.toString().trim()
        val email = binding.editTextRegisterEmail.text.toString().trim()
        val password = binding.editTextRegisterPassword.text.toString() // No trim for password
        val confirmPassword = binding.editTextRegisterConfirmPassword.text.toString()

        if (!validateInput(fullName, email, password, confirmPassword)) {
            return // Validation failed, messages shown within validateInput
        }

        // --- TODO: Perform Actual Registration ---
        // This is where you would integrate with your backend or Firebase for registration.
        // For example, using Firebase Authentication:
        //
        //FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        //    .addOnCompleteListener { task ->
        //        if (task.isSuccessful) {
        //            // Registration success
        //            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
        //            // You might want to also save the full name to Firebase Realtime Database or Firestore
        //            // then navigate to MainActivity or LoginActivity
        //            startActivity(Intent(this, MainActivity::class.java))
        //            finishAffinity() // Clear back stack
        //        } else {
        //            // Registration failed
        //            Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
        //        }
        //    }

        // Placeholder for now:
        Toast.makeText(this, "Registration logic for '$email' would go here.", Toast.LENGTH_LONG).show()
        // Simulate success and navigate to main app or login
        startActivity(Intent(this, MainActivity::class.java)) // Or LoginActivity
        finishAffinity()
    }

    private fun validateInput(fullName: String, email: String, password: String, confirmPassword: String): Boolean {
        if (fullName.isEmpty()) {
            binding.editTextRegisterFullName.error = "Full name is required"
            binding.editTextRegisterFullName.requestFocus()
            return false
        }

        if (email.isEmpty()) {
            binding.editTextRegisterEmail.error = "Email is required"
            binding.editTextRegisterEmail.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextRegisterEmail.error = "Enter a valid email address"
            binding.editTextRegisterEmail.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            binding.editTextRegisterPassword.error = "Password is required"
            binding.editTextRegisterPassword.requestFocus()
            return false
        }

        if (password.length < 6) { // Example: minimum password length
            binding.editTextRegisterPassword.error = "Password must be at least 6 characters"
            binding.editTextRegisterPassword.requestFocus()
            return false
        }

        if (confirmPassword.isEmpty()) {
            binding.editTextRegisterConfirmPassword.error = "Confirm password is required"
            binding.editTextRegisterConfirmPassword.requestFocus()
            return false
        }

        if (password != confirmPassword) {
            binding.editTextRegisterConfirmPassword.error = "Passwords do not match"
            binding.editTextRegisterConfirmPassword.requestFocus()
            // Optionally clear both password fields
            // binding.editTextRegisterPassword.text.clear()
            // binding.editTextRegisterConfirmPassword.text.clear()
            return false
        }
        // Clear previous errors if any
        binding.editTextRegisterFullName.error = null
        binding.editTextRegisterEmail.error = null
        binding.editTextRegisterPassword.error = null
        binding.editTextRegisterConfirmPassword.error = null

        return true
    }
}

