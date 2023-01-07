package com.example.trained.ui.screen.firstStartApp.dayConfig


import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentDayConfigBinding
import com.example.trained.ui.adapter.WorkoutConfigAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DayConfigFragment :
    BaseFragment<FragmentDayConfigBinding>
        (FragmentDayConfigBinding::inflate) {

    private val viewModel: DayConfigViewModel by viewModels()
    private val adapter = WorkoutConfigAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
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