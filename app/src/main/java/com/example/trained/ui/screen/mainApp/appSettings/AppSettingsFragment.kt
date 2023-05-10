package com.example.trained.ui.screen.mainApp.appSettings

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentAppSettingsBinding
import com.example.trained.ui.MainActivity
import com.example.trained.utils.ThemeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppSettingsFragment :
    BaseFragment<FragmentAppSettingsBinding>
        (FragmentAppSettingsBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("appSettingsFragment", "onViewCreated")
        loadTheme()

        binding.upBar.imageButton.setOnClickListener { mainNavController.popBackStack() }
        binding.upBar.textView6.text = getString(R.string.settings_app)
    }

    private fun loadTheme() {
        val loadTheme = (requireActivity() as MainActivity).loadDataThemeState()
        val saveTheme = (requireActivity() as MainActivity)
        val loadDisableScreen = (requireActivity() as MainActivity).getStateDisableScreen()

        binding.switchStateDisable.isChecked = loadDisableScreen
        binding.switchStateDisable.setOnCheckedChangeListener { _, isChecked ->
            (requireActivity() as MainActivity).apply {
                saveStateDisableScreen(isChecked)
                setTheme()
            }
        }


            if (loadTheme == null) {
                saveTheme.saveDateThemeState(ThemeState.SYSTEM)
                binding.rBSystem.isChecked = true
            } else {
                when (loadTheme) {
                    "SYSTEM" -> {
                        binding.rBSystem.isChecked = true
                    }

                    "DARK" -> {
                        binding.rBDark.isChecked = true
                    }

                    "WHITE" -> {
                        binding.rBWhite.isChecked = true
                    }
                }


            binding.rBSystem.setOnClickListener {
                if(binding.rBSystem.isChecked) {
                    binding.rBDark.isChecked = false
                    binding.rBWhite.isChecked = false
                    saveTheme.saveDateThemeState(ThemeState.SYSTEM)
                    saveTheme.setTheme()
                }
            }

            binding.rBDark.setOnClickListener {
                if(binding.rBDark.isChecked) {
                    binding.rBSystem.isChecked = false
                    binding.rBWhite.isChecked = false
                    saveTheme.saveDateThemeState(ThemeState.DARK)
                    saveTheme.setTheme()
                }
            }

            binding.rBWhite.setOnClickListener {
                if(binding.rBWhite.isChecked) {
                    binding.rBSystem.isChecked = false
                    binding.rBDark.isChecked = false
                    saveTheme.saveDateThemeState(ThemeState.WHITE)
                    saveTheme.setTheme()
                }
            }
        }
    }

//    private fun saveTheme() {
//        val saveTheme = (requireActivity() as MainActivity)
//
//        when (binding.ragioGroop.checkedRadioButtonId) {
//            R.id.rBDark -> {
//                saveTheme.saveDateThemeState(ThemeState.DARK)
//            }
//
//            R.id.rBSystem -> {
//                saveTheme.saveDateThemeState(ThemeState.SYSTEM)
//            }
//
//            R.id.rBWhile -> {
//                saveTheme.saveDateThemeState(ThemeState.WHITE)
//            }
//        }
//    }



    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("destroyView", "profileFragmentDestroy")
        Log.d("appSettingsFragment", "onDestroyView")
//        saveTheme()
    }
}

