package com.example.feature_main_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class StartMainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        createBottomSheet()
    }

    private fun createBottomSheet() {
            val bottomSheet = findViewById<ConstraintLayout>(R.id.bottom_sheet_include)
            BottomSheetBehavior.from(bottomSheet).apply {
                peekHeight = 200
                this.state = BottomSheetBehavior.STATE_COLLAPSED
            }
    }
}