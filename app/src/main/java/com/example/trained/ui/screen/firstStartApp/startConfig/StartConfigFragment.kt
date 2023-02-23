package com.example.trained.ui.screen.firstStartApp.startConfig

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentStartConfigBinding
import com.example.trained.ui.MainViewModel
import com.example.trained.ui.adapter.WorkoutConfigAdapter

class StartConfigFragment :
    BaseFragment<FragmentStartConfigBinding>
        (FragmentStartConfigBinding::inflate) {

    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }
    private val adapter = WorkoutConfigAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerTraine.adapter = adapter
        binding.recyclerTraine.layoutManager = GridLayoutManager(context, 2)

        viewModel.getDayWorkout()
        viewModel.dayWorkout.observe(viewLifecycleOwner) { result ->
            result?.let { adapter.setWorkout(it.workout) }
        }

        binding.matButtonNext2.setOnClickListener {
            mainNavController.navigate(R.id.action_dayConfigFragment_to_dayAddWorkoutFragment)
        }
    }
}