package com.pet_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.feature_main_screen.presentation.StartMainScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startMainScreenActivity()
    }


    private fun startMainScreenActivity() {
        startActivity(
            Intent(this, StartMainScreen::class.java)
        )
    }
}