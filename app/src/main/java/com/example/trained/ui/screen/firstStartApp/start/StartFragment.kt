package com.example.trained.ui.screen.firstStartApp.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentStartBinding
import com.example.trained.ui.MainViewModel


class StartFragment :
    BaseFragment<FragmentStartBinding>
        (FragmentStartBinding::inflate) {
    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startSettingWorkout()

        binding.matButtonNext.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_startFragment_to_profileSettingsFragment)
        }
    }
}