package com.example.mentalhealthapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class HealthResourcesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_resources)

        setupResourceButtons()
        setupBackButton()
    }

    private fun setupResourceButtons() {
        val selectedResourceText = findViewById<TextView>(R.id.selectedResourceText)
        val healthResourcesButton = findViewById<Button>(R.id.buttonHealthResources)
        val supportGroupsButton = findViewById<Button>(R.id.buttonSupportGroups)
        val hotlineButton = findViewById<Button>(R.id.buttonHotline)

        healthResourcesButton.setOnClickListener {
            selectedResourceText.text = "📚 Health Resources Selected\n\nAccess educational content, articles, and guides about mental health, wellness tips, coping strategies, and professional advice to support your mental health journey."
            Toast.makeText(this, "Opening Health Resources...", Toast.LENGTH_SHORT).show()
        }

        supportGroupsButton.setOnClickListener {
            selectedResourceText.text = "👥 Support Groups Selected\n\nConnect with others who understand your experiences. Find local and online support groups, peer counseling, community forums, and safe spaces to share and heal together."
            Toast.makeText(this, "Finding Support Groups...", Toast.LENGTH_SHORT).show()
        }

        hotlineButton.setOnClickListener {
            selectedResourceText.text = "📞 Crisis Hotline Selected\n\n24/7 Crisis Support:\n• National Suicide Prevention Lifeline: 988\n• Crisis Text Line: Text HOME to 741741\n• Emergency Services: 911\n\nYou are not alone. Help is available."
            Toast.makeText(this, "Crisis support numbers displayed", Toast.LENGTH_SHORT).show()

            // Optional: You could add functionality to directly call a hotline
            // val intent = Intent(Intent.ACTION_DIAL)
            // intent.data = Uri.parse("tel:988")
            // startActivity(intent)
        }
    }

    private fun setupBackButton() {
        val backButton = findViewById<Button>(R.id.buttonBackToMain)
        backButton.setOnClickListener {
            finish() // Return to previous activity (MainActivity)
        }
    }
}
