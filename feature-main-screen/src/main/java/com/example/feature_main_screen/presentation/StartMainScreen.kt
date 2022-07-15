package com.example.feature_main_screen.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ActivityMainScreenBinding
import com.example.feature_main_screen.databinding.StageInfoLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
/*
        vm.rocket.observe(this) {
            Log.d("AAAA", "${it.data} размер данных дошедших до вью")
            binding.bottomSheetInclude.textRocketName.text = it.data?.get(0)?.company

            imageViewer(it.imageUlrList)
            binding.bottomSheetInclude.textRocketName.text = it.rocketName
            binding.bottomSheetInclude.includedHorizontal.textHeightVal.text = it.height.toString()
            binding.bottomSheetInclude.includedHorizontal.textDiameterVal.text =
                it.diameter.toString()
            binding.bottomSheetInclude.includedHorizontal.textMassVal.text = it.mass.toString()
            binding.bottomSheetInclude.includedHorizontal.textPayloadVal.text =
                it.payload.toString()
            binding.bottomSheetInclude.textFirstFlightVal.text = it.firstFlight
            binding.bottomSheetInclude.textCountryVal.text = it.country
            binding.bottomSheetInclude.textCostPerLaunchVal.text =
                getString(R.string.cost_per_launch_val, ((it.costPerLaunch) / 1000000))

            fun addStageField(view: View, nextStage: Int) {
                bindingStage = StageInfoLayoutBinding.bind(view)
                bindingStage.textStage.text = getString(
                    R.string.stage_name,
                    resources.getStringArray(R.array.number_stage)[nextStage]
                )
                bindingStage.textEnginesVal.text = it.stageInfo[nextStage].engines.toString()
                bindingStage.textFuelAmountTonsVal.text = it.stageInfo[nextStage].fuelAmountTons.toString()
                bindingStage.textBurnTimeSecVal.text = it.stageInfo[nextStage].burnTimeSec.toString()
                binding.bottomSheetInclude.linearLayout.addView(view)
            }

            for (nextStage in 0 until it.stageCount) {
                val stageLayoutInflater = layoutInflater.inflate(R.layout.stage_info_layout, null, false)
                addStageField(stageLayoutInflater, nextStage)
            }
            val buttonLayoutInflater = layoutInflater.inflate(R.layout.button_launch, null)
            binding.bottomSheetInclude.linearLayout.addView(buttonLayoutInflater)



        }

 */

            vm.rocket.observe(this@StartMainScreen) { it ->
                //Log.d("AAAA", "${it.data}")
                if (!it.data.isNullOrEmpty()){
                    binding.bottomSheetInclude.textRocketName.text = it.data[2].name
                    imageViewer(it.data[2].flickrImage)

                }
            }

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