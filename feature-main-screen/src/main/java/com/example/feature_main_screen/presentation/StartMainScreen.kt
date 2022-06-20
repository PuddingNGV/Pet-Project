package com.example.feature_main_screen.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ActivityMainScreenBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso


class StartMainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding

    //private val rocketRepo by lazy { RocketRepoImpl(applicationContext) }
    //private val getDataUseCase by lazy { GetDataUseCase(rocketRepo) }
    private lateinit var vm: MainScreenViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root

        vm = ViewModelProvider(
            this,
            MainScreenViewModelFactory(this)
        ).get(MainScreenViewModel::class.java)

        setContentView(view)
        createBottomSheet()

        vm.dataRocketPackLive.observe(this, Observer {
            binding.bottomSheetInclude.textRocketName.text = it.rocketName
            binding.bottomSheetInclude.includedHorizontal.textHeightVal.text = it.height.toString()
            binding.bottomSheetInclude.includedHorizontal.textDiameterVal.text =
                it.diameter.toString()
            binding.bottomSheetInclude.includedHorizontal.textWeightVal.text = it.weight.toString()
            binding.bottomSheetInclude.includedHorizontal.textPayloadVal.text =
                it.payload.toString()
            binding.bottomSheetInclude.textFirstFlightVal.text = it.firstFlight
            binding.bottomSheetInclude.textCountryVal.text = it.country
            binding.bottomSheetInclude.textCostPerLaunchVal.text =
                getString(R.string.cost_per_launch_val, ((it.costPerLaunch) / 1000000))

            binding.bottomSheetInclude.includedFirstStage.textEnginesVal.text =
                it.stageInfo[0].engines.toString()
            binding.bottomSheetInclude.includedFirstStage.textFuelAmountTonsVal.text =
                it.stageInfo[0].fuelAmountTons.toString()
            binding.bottomSheetInclude.includedFirstStage.textBurnTimeSecVal.text =
                it.stageInfo[0].burnTimeSec.toString()

            binding.bottomSheetInclude.includedSecondStage.textEnginesVal.text =
                it.stageInfo[1].engines.toString()
            binding.bottomSheetInclude.includedSecondStage.textFuelAmountTonsVal.text =
                it.stageInfo[1].fuelAmountTons.toString()
            binding.bottomSheetInclude.includedSecondStage.textBurnTimeSecVal.text =
                it.stageInfo[1].burnTimeSec.toString()

            imageViewer(it.imageUlrList)
        })
    }

    private fun createBottomSheet() {
        val bottomSheet = findViewById<ConstraintLayout>(R.id.bottom_sheet_include)
        BottomSheetBehavior.from(bottomSheet).apply {
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun imageViewer(imageList: List<String>) {

        Picasso.get().load(imageList[imageList.indices.random()]).into(binding.imageRocket)
        binding.imageRocket.setOnClickListener {
            vm.switchImage(imageList).into(binding.imageRocket)
        }
    }
}