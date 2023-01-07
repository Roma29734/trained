package com.example.trained.ui.screen.workout.choseWorkout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.model.transit.WorkoutTransitionModel
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentChoseWorkoutBinding
import com.example.trained.ui.adapter.WorkoutChoseStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChoseWorkoutFragment :
    BaseFragment<FragmentChoseWorkoutBinding>
        (FragmentChoseWorkoutBinding::inflate) {

    private val viewModel: ChoseWorkoutViewModel by viewModels()
    private val adapter = WorkoutChoseStateAdapter()

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
        viewModel.workout?.observe(viewLifecycleOwner) {
            adapter.setWorkout(it.workout)
        }
    }
}