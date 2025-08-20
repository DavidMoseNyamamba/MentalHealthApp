package com.example.mentalhealthapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Directly setup the main app with tabs - no login screen here
        setupTabButtons()
    }

    private fun setupTabButtons() {
        val tabContent = findViewById<TextView>(R.id.tabContent)
        val trackerButton = findViewById<Button>(R.id.tabTracker)
        val resourcesButton = findViewById<Button>(R.id.tabResources)
        val profileButton = findViewById<Button>(R.id.tabProfile)

        trackerButton.setOnClickListener {
            tabContent.text = "Mood Tracker\n\nTrack your daily mood and mental health progress here."
        }

        resourcesButton.setOnClickListener {
            tabContent.text = "Resources\n\nFind helpful mental health resources and articles here."
        }

        profileButton.setOnClickListener {
            tabContent.text = "Profile\n\nManage your profile and app settings here."
        }

        // Set default tab content
        tabContent.text = "Mood Tracker\n\nTrack your daily mood and mental health progress here."
    }
}
