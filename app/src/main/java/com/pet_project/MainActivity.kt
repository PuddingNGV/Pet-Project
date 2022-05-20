package com.pet_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.feature_main_screen.*

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