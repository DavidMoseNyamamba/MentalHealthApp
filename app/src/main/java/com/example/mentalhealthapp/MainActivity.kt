package com.example.mentalhealthapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mentalhealthapp.ui.theme.MentalHealthAppTheme
import com.example.mentalhealthapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        auth = FirebaseManager.auth

        // Check if user is already logged in
        if (auth.currentUser == null) {
            // User not logged in, show welcome screen with login/register options
            setupWelcomeScreen()
        } else {
            // User is logged in, show main app content
            setupMainAppContent()
        }
    }

    private fun setupWelcomeScreen() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun setupMainAppContent() {
        // For logged-in users, you can either show Compose UI or create a separate layout
        // For now, let's create a simple logged-in state
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide login/register buttons and show user info
        binding.buttonLogin.text = "Logout"
        binding.buttonRegister.text = "Profile"

        val user = auth.currentUser
        binding.textViewWelcome.text = "Welcome back, ${user?.displayName ?: user?.email}!"

        binding.buttonLogin.setOnClickListener {
            // Logout functionality
            auth.signOut()
            // Restart activity to show welcome screen
            recreate()
        }

        binding.buttonRegister.setOnClickListener {
            // Navigate to profile or settings (you can create this activity later)
            // For now, just show a message
            android.widget.Toast.makeText(this, "Profile feature coming soon!", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // Check authentication state when returning to this activity
        if (auth.currentUser == null && ::binding.isInitialized) {
            setupWelcomeScreen()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Welcome to $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MentalHealthAppTheme {
        Greeting("Mental Health App")
    }
}
