package com.example.mentalhealthapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MoodTrackerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_tracker)

        setupMoodButtons()
        setupBackButton()
    }

    private fun setupMoodButtons() {
        val selectedMoodText = findViewById<TextView>(R.id.selectedMoodText)
        val happyButton = findViewById<Button>(R.id.buttonHappy)
        val sadButton = findViewById<Button>(R.id.buttonSad)
        val anxiousButton = findViewById<Button>(R.id.buttonAnxious)
        val angryButton = findViewById<Button>(R.id.buttonAngry)
        val neutralButton = findViewById<Button>(R.id.buttonNeutral)

        happyButton.setOnClickListener {
            selectedMoodText.text = "You selected: 😊 Happy\nGreat to hear you're feeling positive today!"
            Toast.makeText(this, "Mood logged: Happy", Toast.LENGTH_SHORT).show()
        }

        sadButton.setOnClickListener {
            selectedMoodText.text = "You selected: 😢 Sad\nIt's okay to feel sad sometimes. Take care of yourself."
            Toast.makeText(this, "Mood logged: Sad", Toast.LENGTH_SHORT).show()
        }

        anxiousButton.setOnClickListener {
            selectedMoodText.text = "You selected: 😰 Anxious\nTry some deep breathing exercises. You've got this!"
            Toast.makeText(this, "Mood logged: Anxious", Toast.LENGTH_SHORT).show()
        }

        angryButton.setOnClickListener {
            selectedMoodText.text = "You selected: 😡 Angry\nTake a moment to breathe and reflect on what's bothering you."
            Toast.makeText(this, "Mood logged: Angry", Toast.LENGTH_SHORT).show()
        }

        neutralButton.setOnClickListener {
            selectedMoodText.text = "You selected: 😐 Neutral\nFeeling balanced today. That's perfectly normal."
            Toast.makeText(this, "Mood logged: Neutral", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBackButton() {
        val backButton = findViewById<Button>(R.id.buttonBackToMain)
        backButton.setOnClickListener {
            finish() // Return to previous activity (MainActivity)
        }
    }
}
