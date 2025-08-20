package com.example.mentalhealthapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tabContent: TextView
    private lateinit var tabTracker: Button
    private lateinit var tabResources: Button
    private lateinit var tabProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        tabContent = findViewById(R.id.tabContent)
        tabTracker = findViewById(R.id.tabTracker)
        tabResources = findViewById(R.id.tabResources)
        tabProfile = findViewById(R.id.tabProfile)
    }

    private fun setupClickListeners() {
        tabTracker.setOnClickListener {
            startActivity(Intent(this, MoodTrackerActivity::class.java))
        }

        tabResources.setOnClickListener {
            startActivity(Intent(this, HealthResourcesActivity::class.java))
        }

        tabProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}
