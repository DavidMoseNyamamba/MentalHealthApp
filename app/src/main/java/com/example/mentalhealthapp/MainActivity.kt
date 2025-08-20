package com.example.mentalhealthapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Directly setup the main app with tabs - no login screen here
        setupTabButtons()
    }

    private fun setupTabButtons() {
        val trackerButton = findViewById<Button>(R.id.tabTracker)
        val resourcesButton = findViewById<Button>(R.id.tabResources)
        val profileButton = findViewById<Button>(R.id.tabProfile)

        trackerButton.setOnClickListener {
            // Navigate to MoodTrackerActivity
            startActivity(Intent(this, MoodTrackerActivity::class.java))
        }

        resourcesButton.setOnClickListener {
            // Navigate to HealthResourcesActivity
            startActivity(Intent(this, HealthResourcesActivity::class.java))
        }

        profileButton.setOnClickListener {
            // Navigate to ProfileActivity
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}
