package com.example.feature_main_screen.presentation

import android.content.Context
import android.content.SharedPreferences
import com.example.feature_settings_screen.presentation.*
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
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

private const val SHARED_PREFS_SETTINGS = "shared_prefs_settings"
private const val KEY_HEIGHT_PARAM = "height_param_settings"
private const val KEY_DIAMETER_PARAM = "diameter_param_settings"
private const val KEY_MASS_PARAM = "mass_param_settings"
private const val KEY_PAYLOAD_PARAM = "payload_param_settings"


@AndroidEntryPoint
class StartMainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var bindingStage: StageInfoLayoutBinding

    private lateinit var preferencesSettings: SharedPreferences

    private val preferencesListenerSettings = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == KEY_HEIGHT_PARAM) {
            binding.bottomSheetInclude.includedHorizontal.textHeight.text = getString(R.string.height_name, (changeParamUnitOfLength(preferencesSettings.getBoolean(key, true))))
        }
        if (key == KEY_DIAMETER_PARAM) {
            binding.bottomSheetInclude.includedHorizontal.textDiameter.text = getString(R.string.diameter_name, (changeParamUnitOfLength(preferencesSettings.getBoolean(key, true))))
        }
        if (key == KEY_MASS_PARAM) {
            binding.bottomSheetInclude.includedHorizontal.textMass.text = getString(R.string.mass_name, (changeParamUnitOfMass(preferencesSettings.getBoolean(key, true))))
        }
        if (key == KEY_PAYLOAD_PARAM) {
            binding.bottomSheetInclude.includedHorizontal.textPayload.text = getString(R.string.payload_name, (changeParamUnitOfMass(preferencesSettings.getBoolean(key, true))))
        }

    }

    private fun changeParamUnitOfMass(metricSystem: Boolean) = if (metricSystem) {
            getString(R.string.kg)
        } else getString(R.string.lb)

    private fun changeParamUnitOfLength(metricSystem: Boolean) = if (metricSystem) {
        getString(R.string.m)
    } else getString(R.string.ft)



    private val vm: MainScreenViewModel by viewModels()
    private val vmSettings: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        preferencesSettings = getSharedPreferences(SHARED_PREFS_SETTINGS, Context.MODE_PRIVATE)
        preferencesSettings.registerOnSharedPreferenceChangeListener(preferencesListenerSettings)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        createBottomSheet()

        binding.bottomSheetInclude.imageButton.setOnClickListener {
            val dialog = SettingsDialogFragment()
            dialog.show(supportFragmentManager, "settingsDialog")
        }

        vmSettings.settingsLiveData.observe(this@StartMainScreen) { it ->
            when (it.heightParam) {
                true -> binding.bottomSheetInclude.includedHorizontal.textHeight.text = getString(R.string.height_name, (getString(R.string.m)))
                false -> binding.bottomSheetInclude.includedHorizontal.textHeight.text = getString(R.string.height_name, (getString(R.string.ft)))
            }
            when (it.diameterParam) {
                true -> binding.bottomSheetInclude.includedHorizontal.textDiameter.text = getString(R.string.diameter_name, (getString(R.string.m)))
                false -> binding.bottomSheetInclude.includedHorizontal.textDiameter.text = getString(R.string.diameter_name, (getString(R.string.ft)))
            }
            when (it.massParam) {
                true -> binding.bottomSheetInclude.includedHorizontal.textMass.text = getString(R.string.mass_name, (getString(R.string.kg)))
                false -> binding.bottomSheetInclude.includedHorizontal.textMass.text = getString(R.string.mass_name, (getString(R.string.lb)))
            }
            when (it.payloadParam) {
                true -> binding.bottomSheetInclude.includedHorizontal.textPayload.text = getString(R.string.payload_name, (getString(R.string.kg)))
                false -> binding.bottomSheetInclude.includedHorizontal.textPayload.text = getString(R.string.payload_name, (getString(R.string.lb)))
            }
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

    override fun onDestroy() {
        super.onDestroy()
        preferencesSettings.unregisterOnSharedPreferenceChangeListener(preferencesListenerSettings)
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