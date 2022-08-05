package com.example.feature_settings_screen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.feature_settings_screen.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsDialogFragment : DialogFragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val vm: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.settingsLiveData.observe(this@SettingsDialogFragment) { it ->
            when (it.heightParam) {
                true -> binding.toggleHeight.check(binding.mHeight.id)
                else -> binding.toggleHeight.check(binding.ftHeight.id)
            }
            when (it.diameterParam) {
                true -> binding.toggleDiameter.check(binding.mDiameter.id)
                else -> binding.toggleDiameter.check(binding.ftDiameter.id)
            }
            when (it.massParam) {
                true -> binding.toggleMass.check(binding.kgMass.id)
                else -> binding.toggleMass.check(binding.lbMass.id)
            }
            when (it.payloadParam) {
                true -> binding.togglePayload.check(binding.kgPayload.id)
                else -> binding.togglePayload.check(binding.lbPayload.id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
    }


    override fun onDestroyView() {
        super.onDestroyView()
            _binding = null
    }

    override fun onResume() {
        super.onResume()
        val listParamSettings = listOf(binding.toggleHeight, binding.toggleDiameter, binding.toggleMass, binding.togglePayload)
        val listMetricSystem = listOf(binding.mHeight.id, binding.mDiameter.id, binding.kgMass.id, binding.kgPayload.id)
        binding.textViewClose.setOnClickListener {
            vm.safe(checkingSettings(listParamSettings, listMetricSystem))
            dismiss()
        }
    }

    private fun checkingSettings(listParamSettings:List<RadioGroup>, listMetricSystem:List<Int>):Map<Int, Boolean> {
        val mapSettings:MutableMap<Int, Boolean> = mutableMapOf()
        for (i in listParamSettings.indices) {
            val paramSettings = listParamSettings[i].checkedRadioButtonId
            when (listMetricSystem.contains(paramSettings)) {
                true -> mapSettings[i] = true
                false -> mapSettings[i] = false
            }
        }
        return mapSettings
    }

}