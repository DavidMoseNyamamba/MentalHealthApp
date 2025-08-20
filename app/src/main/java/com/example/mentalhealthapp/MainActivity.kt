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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // For now, let's use Compose UI
        enableEdgeToEdge()
        setContent {
            MentalHealthAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Mental Health App",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        // If you want to use traditional Views instead, uncomment below and comment out the Compose code above:
        // setContentView(R.layout.activity_main)
        // setupViewBinding()
    }

    // Uncomment this method if you want to use View binding with traditional layout
    /*
    private fun setupViewBinding() {
        // Add your view binding setup here
        // binding.buttonLogin.setOnClickListener {
        //     startActivity(Intent(this, LoginActivity::class.java))
        // }
        //
        // binding.buttonRegister.setOnClickListener {
        //     startActivity(Intent(this, RegisterActivity::class.java))
        // }
    }
    */
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
