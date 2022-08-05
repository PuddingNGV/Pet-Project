package com.example.feature_main_screen.presentation

import com.example.feature_settings_screen.presentation.SettingsDialogFragment
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ActivityMainScreenBinding
import com.example.feature_main_screen.databinding.StageInfoLayoutBinding
import com.example.feature_main_screen.domain.models.RocketInfo
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.example.feature_settings_screen.*
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StartMainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var bindingStage: StageInfoLayoutBinding

    private val vm: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        createBottomSheet()

        binding.bottomSheetInclude.imageButton.setOnClickListener {
            val dialog = SettingsDialogFragment()
            dialog.show(supportFragmentManager, "settingsDialog")
        }

            vm.rocket.observe(this@StartMainScreen) { it ->
                if (!it.data.isNullOrEmpty()) {
                    val dataRocket = it.data[3]

                    imageViewer(dataRocket.imageUlrList)
                    binding.bottomSheetInclude.textRocketName.text = dataRocket.rocketName
                    binding.bottomSheetInclude.includedHorizontal.textHeightVal.text = dataRocket.height.toString()
                    binding.bottomSheetInclude.includedHorizontal.textDiameterVal.text =
                        dataRocket.diameter.toString()
                    binding.bottomSheetInclude.includedHorizontal.textMassVal.text = dataRocket.mass.toString()
                    binding.bottomSheetInclude.includedHorizontal.textPayloadVal.text =
                        dataRocket.payload.toString()
                    binding.bottomSheetInclude.textFirstFlightVal.text = dataRocket.firstFlight
                    binding.bottomSheetInclude.textCountryVal.text = dataRocket.country
                    binding.bottomSheetInclude.textCostPerLaunchVal.text =
                        getString(R.string.cost_per_launch_val, ((dataRocket.costPerLaunch) / 1000000))
                    binding.bottomSheetInclude.linearLayoutStageInfo.removeAllViews()
                    addStageInfoField(dataRocket)
                }
            }

    }

    private fun addStageInfoField(dataRocket: RocketInfo) {
        for (nextStage in 0 until dataRocket.stageCount) {
            val stageLayoutInflater = layoutInflater.inflate(R.layout.stage_info_layout, null, false)
            updateInfoStageField(stageLayoutInflater, nextStage, dataRocket)
        }
        val buttonLayoutInflater = layoutInflater.inflate(R.layout.button_launch, null)
        binding.bottomSheetInclude.linearLayoutStageInfo.addView(buttonLayoutInflater)
    }

    private fun updateInfoStageField(view: View, nextStage: Int, dataRocket: RocketInfo) {
        bindingStage = StageInfoLayoutBinding.bind(view)
        bindingStage.textStage.text = getString(
            R.string.stage_name,
            resources.getStringArray(R.array.number_stage)[nextStage]
        )
        bindingStage.textEnginesVal.text = dataRocket.stageInfo[nextStage].engines.toString()
        bindingStage.textFuelAmountTonsVal.text = dataRocket.stageInfo[nextStage].fuelAmountTons.toString()
        bindingStage.textBurnTimeSecVal.text = dataRocket.stageInfo[nextStage].burnTimeSec.toString()
        binding.bottomSheetInclude.linearLayoutStageInfo.addView(view, nextStage)
    }

    private fun createBottomSheet() {
        val bottomSheet = findViewById<ConstraintLayout>(R.id.bottom_sheet_include)
        BottomSheetBehavior.from(bottomSheet).apply {
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun imageViewer(imageList: List<String>) {

        Picasso.get().load(imageList[imageList.indices.random()]).into(binding.imageRocket)
        binding.imageRocket.setOnClickListener {
            vm.switchImage(imageList).into(binding.imageRocket)
        }
    }

}