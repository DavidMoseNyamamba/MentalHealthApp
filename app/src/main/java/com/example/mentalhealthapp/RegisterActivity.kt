package com.example.mentalhealthapp // Replace with your package

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalhealthapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase services
        auth = FirebaseManager.auth
        firestore = FirebaseManager.firestore

        binding.buttonPerformRegister.setOnClickListener {
            handleRegistration()
        }

        binding.textViewLoginLink.setOnClickListener {
            // Navigate back to LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun handleRegistration() {
        val fullName = binding.editTextRegisterFullName.text.toString().trim()
        val email = binding.editTextRegisterEmail.text.toString().trim()
        val password = binding.editTextRegisterPassword.text.toString()
        val confirmPassword = binding.editTextRegisterConfirmPassword.text.toString()

        if (!validateInput(fullName, email, password, confirmPassword)) {
            return // Validation failed
        }

        // Show loading state
        binding.buttonPerformRegister.isEnabled = false
        binding.buttonPerformRegister.text = "Creating Account..."

        // Firebase Authentication - Create user
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration success
                    val user = auth.currentUser

                    // Update user profile with display name
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(fullName)
                        .build()

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { profileTask ->
                            if (profileTask.isSuccessful) {
                                // Save additional user data to Firestore
                                saveUserToFirestore(user.uid, fullName, email)
                            }
                        }
                } else {
                    // Registration failed
                    binding.buttonPerformRegister.isEnabled = true
                    binding.buttonPerformRegister.text = "Register"
                    val errorMessage = task.exception?.message ?: "Registration failed"
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun saveUserToFirestore(userId: String, fullName: String, email: String) {
        val userMap = hashMapOf(
            "fullName" to fullName,
            "email" to email,
            "createdAt" to System.currentTimeMillis(),
            "isActive" to true
        )

        firestore.collection("users").document(userId)
            .set(userMap)
            .addOnSuccessListener {
                binding.buttonPerformRegister.isEnabled = true
                binding.buttonPerformRegister.text = "Register"

                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()

                // Log analytics event
                FirebaseManager.analytics.logEvent("user_registration", null)

                // Navigate to MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            }
            .addOnFailureListener { e ->
                binding.buttonPerformRegister.isEnabled = true
                binding.buttonPerformRegister.text = "Register"
                Toast.makeText(this, "Error saving user data: ${e.message}", Toast.LENGTH_LONG).show()
            }
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

        if (password.length < 6) {
            binding.editTextRegisterPassword.error = "Password must be at least 6 characters"
            binding.editTextRegisterPassword.requestFocus()
            return false
        }

        if (confirmPassword.isEmpty()) {
            binding.editTextRegisterConfirmPassword.error = "Please confirm your password"
            binding.editTextRegisterConfirmPassword.requestFocus()
            return false
        }

        if (password != confirmPassword) {
            binding.editTextRegisterConfirmPassword.error = "Passwords do not match"
            binding.editTextRegisterConfirmPassword.requestFocus()
            return false
        }

        // Clear previous errors
        binding.editTextRegisterFullName.error = null
        binding.editTextRegisterEmail.error = null
        binding.editTextRegisterPassword.error = null
        binding.editTextRegisterConfirmPassword.error = null

        return true
    }
}
