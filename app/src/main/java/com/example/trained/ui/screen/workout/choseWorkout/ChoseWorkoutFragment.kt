package com.example.trained.ui.screen.workout.choseWorkout

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.model.transit.WorkoutTransitionModel
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentChoseWorkoutBinding
import com.example.trained.ui.adapter.WorkoutStateAdapter

class ChoseWorkoutFragment :
    BaseFragment<FragmentChoseWorkoutBinding>
        (FragmentChoseWorkoutBinding::inflate) {

    private val viewModel: ChoseWorkoutViewModel by viewModels { viewModelFactory }
    private val adapter = WorkoutStateAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.callBackDel = {
            val model = WorkoutTransitionModel(
                workoutModel = it,
                timeChill = 0,
                timeWorkout = 0,
            )
            val action =
                ChoseWorkoutFragmentDirections.actionChoseWorkoutFragmentToSettingExerciseFragment(
                    model
                )
            mainNavController.navigate(action)
        }

        binding.upBar.imageButton.setOnClickListener {
            mainNavController.popBackStack()
        }

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(context, 2)

        viewModel.readWorkout()
        viewModel.workout?.observe(viewLifecycleOwner) { result ->
            if (result.isEmpty()) {
                binding.textSupportive.isVisible = true
            } else {
                binding.textSupportive.isVisible = false
                adapter.setWorkout(result)
            }
        }
    }
}