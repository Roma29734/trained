package com.example.trained.ui.screen.mainApp.dayConfig

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.model.transit.TransitUpdateWorkoutModel
import com.example.data.model.transit.TransitWorkoutModel
import com.example.data.toTransit
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
    private val args: DayConfigFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerWorkout.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerWorkout.adapter = adapter

        viewModel.readWorkout(args.week)
        viewModel.workout.observe(viewLifecycleOwner) { result ->
            adapter.callBackDel = {
                val model = TransitUpdateWorkoutModel(
                    workoutModel = result.toTransit(),
                    id = it
                )
                val action =
                    DayConfigFragmentDirections.actionDayConfigFragmentToUpdateConfigFragment(model)
                mainNavController.navigate(action)
            }
            binding.matButtonNext2.setOnClickListener {
                val action =
                    DayConfigFragmentDirections.actionDayConfigFragmentToAddWorkoutFragment(result.toTransit())
                mainNavController.navigate(action)
            }
            adapter.setWorkout(result.workout)
        }

        binding.include2.imageButton.setOnClickListener {
            mainNavController.popBackStack()
        }
    }
}