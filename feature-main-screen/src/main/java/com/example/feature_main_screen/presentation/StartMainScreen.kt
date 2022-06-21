package com.example.feature_main_screen.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
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
            imageViewer(it.imageUlrList)
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

            fun addStageField(view: View, nextStage: Int) {
                val stageLayoutInflater = layoutInflater.inflate(R.layout.stage_info_layout, null)
                stageLayoutInflater.findViewById<TextView>(R.id.textStage).text = getString(
                    R.string.stage_name,
                    resources.getStringArray(R.array.number_stage)[nextStage]
                )
                stageLayoutInflater.findViewById<TextView>(R.id.textEnginesVal).text =
                    it.stageInfo[nextStage].engines.toString()
                stageLayoutInflater.findViewById<TextView>(R.id.textFuelAmountTonsVal).text =
                    it.stageInfo[nextStage].fuelAmountTons.toString()
                stageLayoutInflater.findViewById<TextView>(R.id.textBurnTimeSecVal).text =
                    it.stageInfo[nextStage].burnTimeSec.toString()

                binding.bottomSheetInclude.linearLayout.addView(stageLayoutInflater)
            }

            for (nextStage in 0 until it.stageCount) {
                addStageField(view, nextStage)
            }

            val buttonLayoutInflater = layoutInflater.inflate(R.layout.button_launch, null)
            binding.bottomSheetInclude.linearLayout.addView(buttonLayoutInflater)
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