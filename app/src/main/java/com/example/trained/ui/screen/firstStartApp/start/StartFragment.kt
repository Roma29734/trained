package com.example.trained.ui.screen.firstStartApp.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment :
    BaseFragment<FragmentStartBinding>
        (FragmentStartBinding::inflate) {
    private val viewModel: StartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startSettingWorkout()

        binding.matButtonNext.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_startFragment_to_profileSettingsFragment)
        }
    }
}