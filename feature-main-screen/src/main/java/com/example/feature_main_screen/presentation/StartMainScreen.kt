package com.example.feature_main_screen.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.feature_main_screen.R
import com.example.feature_main_screen.data.remote.RocketRepoImpl
import com.example.feature_main_screen.databinding.ActivityMainScreenBinding
import com.example.feature_main_screen.domain.usecase.GetDataUseCase
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import com.example.feature_main_screen.data.remote.responce.RocketResponse
import com.example.feature_main_screen.data.remote.ApiHelper
import com.example.feature_main_screen.data.remote.RetrofitBuilder
import com.example.feature_main_screen.data.remote.ApiRockets



class StartMainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding

    //private val rocketRepo by lazy { RocketRepoImpl(applicationContext) }
    //private val getDataUseCase by lazy { GetDataUseCase(rocketRepo) }
    private lateinit var vm:MainScreenViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root

        vm = ViewModelProvider(this, MainScreenViewModelFactory(this)).get(MainScreenViewModel::class.java)


        setContentView(view)
        createBottomSheet()

        vm.dataRocketPackLive.observe(this, Observer {
            binding.bottomSheetInclude.textRocketName.text = it.rocketName
            binding.bottomSheetInclude.includedHorizontal.textHeightVal.text = it.height.toString()
            binding.bottomSheetInclude.includedHorizontal.textDiameterVal.text = it.diameter.toString()
            binding.bottomSheetInclude.includedHorizontal.textWeightVal.text = it.weight.toString()
            binding.bottomSheetInclude.includedHorizontal.textPayloadVal.text = it.payload.toString()
            binding.bottomSheetInclude.textFirstFlightVal.text = it.firstFlight
            binding.bottomSheetInclude.textCountryVal.text = it.country

            binding.bottomSheetInclude.textCostPerLaunchVal.text = getString(R.string.cost_per_launch_val, ((it.costPerLaunch) / 1000000))
            binding.bottomSheetInclude.includedFirstStage.textEnginesVal.text = it.firstStageInfo.engines.toString()
            binding.bottomSheetInclude.includedFirstStage.textFuelAmountTonsVal.text = it.firstStageInfo.fuelAmountTons.toString()
            binding.bottomSheetInclude.includedFirstStage.textBurnTimeSecVal.text = it.firstStageInfo.burnTimeSec.toString()

            binding.bottomSheetInclude.includedSecondStage.textEnginesVal.text = it.secondStageInfo.engines.toString()
            binding.bottomSheetInclude.includedSecondStage.textFuelAmountTonsVal.text = it.secondStageInfo.fuelAmountTons.toString()
            binding.bottomSheetInclude.includedSecondStage.textBurnTimeSecVal.text = it.secondStageInfo.burnTimeSec.toString()
        })

    }



    private fun createBottomSheet() {
            val bottomSheet = findViewById<ConstraintLayout>(R.id.bottom_sheet_include)
            BottomSheetBehavior.from(bottomSheet).apply {
                peekHeight = 200
                this.state = BottomSheetBehavior.STATE_COLLAPSED
            }
    }
}