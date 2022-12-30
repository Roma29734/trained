package com.example.trained.ui.screen.config

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentConfigBinding
import com.example.trained.ui.adapter.WorkoutConfigAdapter
import com.example.trained.ui.adapter.WorkoutStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfigFragment :
    BaseFragment<FragmentConfigBinding>
        (FragmentConfigBinding::inflate) {

    private val viewModel: ConfigViewModel by viewModels()
    private val adapter = WorkoutConfigAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerTraine.adapter = adapter
        binding.recyclerTraine.layoutManager = GridLayoutManager(context, 2)

        viewModel.getDayWorkout()
        viewModel.dayWorkout.observe(viewLifecycleOwner) { adapter.setWorkout(it) }

        binding.matButtonNext2.setOnClickListener {
            mainNavController.navigate(R.id.action_navFragment_to_addWorkoutFragment)
        }
    }
}