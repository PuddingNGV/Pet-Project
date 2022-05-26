package com.example.feature_main_screen.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.feature_main_screen.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.example.feature_main_screen.domain.usecase.GetDataUseCase
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.databinding.ActivityMainScreenBinding
import com.example.feature_main_screen.data.RocketRepoImpl

class StartMainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding

    private val rocketRepo by lazy { RocketRepoImpl(applicationContext) }
    private val getDataUseCase by lazy { GetDataUseCase(rocketRepo) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root



        setContentView(view)
        createBottomSheet()
        getData()
    }

    private fun createBottomSheet() {
            val bottomSheet = findViewById<ConstraintLayout>(R.id.bottom_sheet_include)
            BottomSheetBehavior.from(bottomSheet).apply {
                peekHeight = 200
                this.state = BottomSheetBehavior.STATE_COLLAPSED
            }
    }
    private fun getData() {
        val rocketData: RocketInfo = getDataUseCase.execute()
        println("This is $rocketData")
        binding.bottomSheetInclude.textRocketName.text = rocketData.rocketName
        binding.bottomSheetInclude.textHeightVal.text = rocketData.height.toString()
        binding.bottomSheetInclude.textDiameterVal.text = rocketData.diameter.toString()
        binding.bottomSheetInclude.textWeightVal.text = rocketData.weight.toString()
        binding.bottomSheetInclude.textPayloadVal.text = rocketData.payload.toString()
        binding.bottomSheetInclude.textFirstFlightVal.text = rocketData.firstFlight.toString()
        binding.bottomSheetInclude.textCountryVal.text = rocketData.country
        binding.bottomSheetInclude.textCostPerLaunchVal.text = rocketData.costPerLaunch.toString()
        binding.bottomSheetInclude.includeFirstStage.textEnginesVal.text = rocketData.firstStageInfo.engines.toString()
        binding.bottomSheetInclude.includeFirstStage.textFuelAmountTonsVal.text = rocketData.firstStageInfo.fuelAmountTons.toString()
        binding.bottomSheetInclude.includeFirstStage.textBurnTimeSecVal.text = rocketData.firstStageInfo.burnTimeSec.toString()

        binding.bottomSheetInclude.includeSecondStage.textEnginesVal.text = rocketData.secondStageInfo.engines.toString()
        binding.bottomSheetInclude.includeSecondStage.textFuelAmountTonsVal.text = rocketData.secondStageInfo.fuelAmountTons.toString()
        binding.bottomSheetInclude.includeSecondStage.textBurnTimeSecVal.text = rocketData.secondStageInfo.burnTimeSec.toString()
    }
}